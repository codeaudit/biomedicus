/*
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.umn.biomedicus.model.tuples;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Unit test for {@link WordCap}.
 */
public class WordCapTest {

    @Test
    public void testGetWord() throws Exception {
        WordCap wordCap = new WordCap("aWord", false);

        assertEquals(wordCap.getWord(), "aWord");
    }

    @Test
    public void testIsCapitalized() throws Exception {
        WordCap wordCap = new WordCap("aWord", false);

        assertEquals(wordCap.isCapitalized(), false);
    }

    @Test
    public void testEqualsSameObject() throws Exception {
        WordCap wordCap = new WordCap("aWord", false);

        assertTrue(wordCap.equals(wordCap));
    }

    @Test
    public void testEqualsNull() throws Exception {
        WordCap wordCap = new WordCap("aWord", false);

        assertFalse(wordCap.equals(null));
    }

    @Test
    public void testEqualsDifferentType() throws Exception {
        WordCap wordCap = new WordCap("aWord", false);

        assertFalse(wordCap.equals("aString"));
    }

    @Test
    public void testEqualsDifferentWord() throws Exception {
        WordCap first = new WordCap("first", false);
        WordCap second = new WordCap("second", false);

        assertFalse(first.equals(second));
    }

    @Test
    public void testEqualsDifferentCap() throws Exception {
        WordCap first = new WordCap("aWord", true);
        WordCap second = new WordCap("aWord", false);

        assertFalse(first.equals(second));
    }

    @Test
    public void testEquals() throws Exception {
        WordCap first = new WordCap("aWord", true);
        WordCap second = new WordCap("aWord", true);

        assertTrue(first.equals(second));
    }

    @Test
    public void testHashCode() throws Exception {
        WordCap first = new WordCap("aWord", true);
        WordCap second = new WordCap("aWord", true);

        assertTrue(first.hashCode() == second.hashCode());
    }

    @Test
    public void testHashCodeNonEqual() throws Exception {
        WordCap first = new WordCap("aWord", true);
        WordCap second = new WordCap("aWord", false);

        assertFalse(first.hashCode() == second.hashCode());
    }

    @Test
    public void testCompareToWord() throws Exception {
        WordCap first = new WordCap("first", false);
        WordCap second = new WordCap("second", false);

        assertEquals(first.compareTo(second), "first".compareTo("second"));
    }

    @Test
    public void testCompareToCapitalization() throws Exception {
        WordCap first = new WordCap("aWord", true);
        WordCap second = new WordCap("aWord", false);

        assertEquals(first.compareTo(second), Boolean.compare(true, false));
    }

    @Test
    public void testToString() throws Exception {
        WordCap wordCap = new WordCap("aWord", false);

        assertEquals(wordCap.toString(), "WordCap{word='aWord', isCapitalized=false}");
    }

    @Test
    public void testLowercase() throws Exception {
        WordCap wordCap = new WordCap("aWord", false).lowercase();

        assertEquals(wordCap.getWord(), "aword");
    }

    @Test
    public void testLowercaseIgnoreCapitalization() throws Exception {
        WordCap wordCap = new WordCap("aWord", true).lowercaseIgnoreCapitalization();

        assertEquals(wordCap.getWord(), "aword");
        assertEquals(wordCap.isCapitalized(), false);
    }

    @Test
    public void testIdentity() throws Exception {
        WordCap wordCap = new WordCap("aWord", true);
        WordCap same = wordCap.identity();

        assertEquals(wordCap, same);
    }
}