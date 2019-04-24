/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jittagornp.profile.game24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author jittagornp
 */
public class Probability {

    private static interface Callback {

        void call(String element);
    }

    private final List<String> elements;
    private boolean distinct = false;
    private boolean unique = false;
    private Integer size;
    private Set<String> signatures;

    private Probability(List<String> elements) {
        this.elements = new ArrayList<>();
        for(int i=0; i<elements.size(); i++){
            this.elements.add(i + "." + elements.get(i));
        }
    }

    public static Probability ofElements(String... elements) {
        return new Probability(Arrays.asList(elements));
    }

    public static Probability ofElements(List<String> elements) {
        return new Probability(elements);
    }

    public Probability size(int size) {
        this.size = size;
        return this;
    }

    public Probability distinct() {
        this.distinct = true;
        return this;
    }

    public Probability unique() {
        this.unique = true;
        return this;
    }

    private Set<String> getSignatures() {
        if (signatures == null) {
            signatures = new HashSet<>();
        }

        return signatures;
    }

    private boolean contains(String str, String target) {
        if (!str.isEmpty()) {
            String[] els = StringUtils.split(str, ":");
            for (String el : els) {
                if (el.equals(target)) {
                    return true;
                }
            }
        }

        return false;
    }

    private String findSignature(String str) {
        String[] els = StringUtils.split(str, ":");
        Arrays.sort(els);
        return StringUtils.join(els, ":");
    }

    private boolean has(String element) {
        String signature = findSignature(element);
        if (getSignatures().contains(signature)) {
            return true;
        }

        getSignatures().add(signature);
        return false;
    }

    private void walking(String topElement, List<String> elements, int index, Callback callback) {
        if (index < 0) {
            if (unique && has(topElement)) {
                return;
            }

            callback.call(topElement);
            return;
        }

        for (String element : elements) {
            if (distinct && contains(topElement, element)) {
                continue;
            }

            walking(
                    (topElement.isEmpty() ? topElement : topElement + ":") + element,
                    elements,
                    index - 1,
                    callback
            );
        }
    }

    public List<String> find() {
        if (size == null) {
            size = elements.size();
        }

        final List<String> results = new ArrayList<>();
        walking("", elements, size - 1, new Callback() {

            @Override
            public void call(String element) {
                List<String> result = new ArrayList<>();
                String[] els = StringUtils.split(element, ":");
                for(String el : els){
                    String[] split = StringUtils.split(el, ".");
                    result.add(split[1]);
                }
                results.add(StringUtils.join(result, ":"));
            }
        });

        return results;
    }
}
