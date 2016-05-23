package com.IIgarcialII.lsh;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Alejandro Garcia on 5/21/16.
 */
public class JaccardSimilarityCalculatorTest {

        @Test
        public void calculate() throws Exception {

                final boolean[][] matrix = new boolean[3][3];
                matrix[0] = new boolean[]{true, true, true};
                matrix[1] = new boolean[]{true, false, false};
                matrix[2] = new boolean[]{false, true, true};

                final double[][] expected = new double[3][3];
                expected[0] = new double[]{0, 0.3333333333333333, 0.6666666666666666};
                expected[1] = new double[]{0.3333333333333333, 0, 0};
                expected[2] = new double[]{0.6666666666666666, 0, 0};

                final JaccardSimilarityCalculator js = new JaccardSimilarityCalculator();
                final double[][] actual = js.calculate(matrix);

                assertArrayEquals(expected[0], actual[0], 0.001);
                assertArrayEquals(expected[1], actual[1], 0.001);
                assertArrayEquals(expected[1], actual[1], 0.001);

        }

}