/*
 * Copyright (c) 2018 Grumpy Cat Software S.L.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package tsa;

import org.junit.Assert;
import org.junit.Test;

public class NormalizationTest {
    private static final double DELTA = 1e-6;

    @Test
    public void testZnorm() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7};
        long[] dims = {4, 2, 1, 1};
        Array a = new Array(tss, dims);
        double[] result = Normalization.znorm(a).getData();
        double[] expected = {-1.341640786499870, -0.447213595499958, 0.447213595499958, 1.341640786499870};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(result[i], expected[i], DELTA);
            Assert.assertEquals(result[i + 4], expected[i], DELTA);
        }
    }

    @Test
    public void testZnormInPlace() throws Exception {
        double[] tss = {0, 1, 2, 3, 4, 5, 6, 7};
        long[] dims = {4, 2, 1, 1};
        Array a = new Array(tss, dims);
        Normalization.znormInPlace(a);
        double[] result = a.getData();
        Assert.assertEquals(result[0], -1.341640786499870, DELTA);
        Assert.assertEquals(result[1], -0.447213595499958, DELTA);
        Assert.assertEquals(result[2], 0.447213595499958, DELTA);
        Assert.assertEquals(result[3], 1.341640786499870, DELTA);

        Assert.assertEquals(result[4], -1.341640786499870, DELTA);
        Assert.assertEquals(result[5], -0.447213595499958, DELTA);
        Assert.assertEquals(result[6], 0.447213595499958, DELTA);
        Assert.assertEquals(result[7], 1.341640786499870, DELTA);
    }
}
