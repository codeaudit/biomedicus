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

import edu.umn.biomedicus.model.semantics.PartOfSpeech;
import edu.umn.biomedicus.model.text.Token;
import mockit.Expectations;
import mockit.Mocked;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Unit test for {@link PosCap}.
 */
public class PosCapTest {
    @Test
    public void testGetCapitalized() throws Exception {
        PosCap posCap = PosCap.getCapitalized(PartOfSpeech.BOS);

        assertEquals(posCap.getPartOfSpeech(), PartOfSpeech.BOS);
        assertEquals(posCap.isCapitalized(), true);
    }

    @Test
    public void testGetNotCapitalized() throws Exception {
        PosCap posCap = PosCap.getNotCapitalized(PartOfSpeech.BOS);

        assertEquals(posCap.getPartOfSpeech(), PartOfSpeech.BOS);
        assertEquals(posCap.isCapitalized(), false);
    }

    @Test
    public void testCreate() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);

        assertEquals(posCap.getPartOfSpeech(), PartOfSpeech.BOS);
        assertEquals(posCap.isCapitalized(), true);
    }

    @Test
    public void testCreateWithToken(@Mocked Token token) throws Exception {
        new Expectations() {{
            token.isCapitalized(); result = true;
            token.getPartOfSpeech(); result = PartOfSpeech.BOS;
        }};

        PosCap posCap = PosCap.create(token);

        assertEquals(posCap.getPartOfSpeech(), PartOfSpeech.BOS);
        assertEquals(posCap.isCapitalized(), true);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWithTokenNullPOS(@Mocked Token token) throws Exception {
        new Expectations() {{
            token.getPartOfSpeech(); result = null;
        }};

        PosCap.create(token);

        fail();
    }

    @Test
    public void testGetPartOfSpeech() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);

        assertEquals(posCap.getPartOfSpeech(), PartOfSpeech.BOS);
    }

    @Test
    public void testIsCapitalized() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);

        assertEquals(posCap.isCapitalized(), true);
    }

    @Test
    public void testOrdinal() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);

        assertEquals(posCap.ordinal(), 108);
    }

    @Test
    public void testCardinality() throws Exception {
        assertEquals(PosCap.cardinality(), 110);
    }

    @Test
    public void testCreateFromOrdinal() throws Exception {
        PosCap posCap = PosCap.createFromOrdinal(108);

        assertEquals(posCap, PosCap.create(PartOfSpeech.BOS, true));
    }

    @Test
    public void testEqualsSameObject() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.CD, true);
        assertTrue(posCap.equals(posCap));
    }

    @Test
    public void testEquals() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);
        PosCap second = PosCap.getCapitalized(PartOfSpeech.BOS);

        assertTrue(posCap.equals(second));
    }

    @Test
    public void testEqualsNull() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);

        assertFalse(posCap.equals(null));
    }

    @Test
    public void testEqualsNotObject() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);

        assertFalse(posCap.equals("string"));
    }

    @Test
    public void testEqualsFalse() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);
        PosCap second = PosCap.getCapitalized(PartOfSpeech.BBS);

        assertFalse(posCap.equals(second));
    }

    @Test
    public void testEqualsFalseCapitalization() throws Exception {

        PosCap posCap = PosCap.create(PartOfSpeech.BBS, false);
        PosCap second = PosCap.getCapitalized(PartOfSpeech.BBS);

        assertFalse(posCap.equals(second));
    }

    @Test
    public void testHashCodeEqual() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);
        PosCap second = PosCap.getCapitalized(PartOfSpeech.BOS);

        assertTrue(posCap.hashCode() == second.hashCode());
    }

    @Test
    public void testHashCodeUnequal() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);
        PosCap second = PosCap.getCapitalized(PartOfSpeech.BBS);

        assertFalse(posCap.hashCode() == second.hashCode());
    }

    @Test
    public void testCompareTo() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, true);
        PosCap second = PosCap.getCapitalized(PartOfSpeech.BBS);

        assertEquals(posCap.compareTo(second), PartOfSpeech.BOS.compareTo(PartOfSpeech.BBS));
    }

    @Test
    public void testCompareToCapitalization() throws Exception {

        PosCap posCap = PosCap.create(PartOfSpeech.BOS, false);
        PosCap second = PosCap.getCapitalized(PartOfSpeech.BOS);

        assertEquals(posCap.compareTo(second), Boolean.compare(false, true));
    }

    @Test
    public void testToString() throws Exception {
        PosCap posCap = PosCap.create(PartOfSpeech.BOS, false);

        assertEquals(posCap.toString(), "PosCap{partOfSpeech=BOS, capitalized=false}");
    }
}