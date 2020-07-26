package service;

import entity.CatalogProductRelationship;
import entity.Product;
import lombok.Getter;

import java.util.*;

@Getter
public class InvertedIndexService {
    private Map<String, Set<CatalogProductRelationship>> keywordProductMap;
    private List<String> stopWords = Arrays.asList("I", "its", "with", "but", "and", "of", "for", "combined", "any", "are", "is", "same", "to", "do", "does", "did", "only", "in", "into", "upon", "below");

    public InvertedIndexService() {
        this.keywordProductMap = new TreeMap<>();
    }

    public void index(CatalogProductRelationship relationship) {
        Product p = relationship.getProduct();
        Set<String> keywords = new HashSet<>();
        populateKeywords(keywords, p.getTitle());
        populateKeywords(keywords, p.getDescription());
        keywords.stream().forEach(keyword -> {
            if (keywordProductMap.containsKey(keyword))
                keywordProductMap.get(keyword).add(relationship);
            else
                keywordProductMap.put(keyword, new HashSet<>(Arrays.asList(relationship)));
        });
    }

    private void populateKeywords(Set<String> keywords, String word) {
        String[] splitWords = word.toLowerCase().split(" ");
        for (String split : splitWords) {
            if (!stopWords.contains(split))
                keywords.add(split);
        }
    }

    public Set<CatalogProductRelationship> search(String searchQuery) {
        if (keywordProductMap.containsKey(searchQuery)) {
            return keywordProductMap.get(searchQuery);
        } else
            return Collections.emptySet();
    }

    public void updateKeywordMap(Product p) {
        Set<String> keywords = new HashSet<>();
        populateKeywords(keywords, p.getTitle());
        populateKeywords(keywords, p.getDescription());
        Set<CatalogProductRelationship> toBeDeleted = new HashSet<>();
        for (String s : keywords) {
            if (keywordProductMap.containsKey(s)) {
                Set<CatalogProductRelationship> catalogProductRelationships = keywordProductMap.get(s);
                catalogProductRelationships.stream().forEach(relationship -> {
                    if (relationship.getProduct().getId() == p.getId()) {
                        toBeDeleted.add(relationship);
                    }
                });
                catalogProductRelationships.removeAll(toBeDeleted);
            }

        }
    }
}
