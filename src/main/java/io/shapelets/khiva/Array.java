/*
 * Copyright (c) 2019 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva;

import java.util.Objects;

/**
 * Khiva Array Class.
 */
public class Array extends Library implements AutoCloseable {

    private long reference;

    /**
     * Data type of the Array
     */
    public enum Dtype {
        /**
         * Floating point of single precision. khiva.dtype.
         */
        f32,
        /**
         * Complex floating point of single precision. khiva.dtype.
         */
        c32,
        /**
         * Floating point of double precision. khiva.dtype.
         */
        f64,
        /**
         * Complex floating point of double precision. khiva.dtype.
         */
        c64,
        /**
         * Boolean. khiva.dtype.
         */
        b8,
        /**
         * 32 bits Int. khiva.dtype.
         */
        s32,
        /**
         * 32 bits Unsigned Int. khiva.dtype.
         */
        u32,
        /**
         * 8 bits Unsigned Int. khiva.dtype.
         */
        u8,
        /**
         * 64 bits Integer. khiva.dtype.
         */
        s64,
        /**
         * 64 bits Unsigned Int. khiva.dtype.
         */
        u64,
        /**
         * 16 bits Int. khiva.dtype.
         */
        s16,
        /**
         * 16 bits Unsigned Int. khiva.dtype.
         */
        u16
    }

    public Array(double[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }
        this.reference = createArrayFromDouble(arr, dims);
    }

    private int getNumElements(long[] dims) throws Exception {
        long[] arrayDims = Array.dim4(dims);
        int totalSize = 1;
        for (long dim : arrayDims) totalSize *= dim;
        return totalSize;
    }

    public Array(float[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }
        this.reference = createArrayFromFloat(arr, dims);
    }

    public Array(int[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }
        this.reference = createArrayFromInt(arr, dims);
    }

    public Array(FloatComplex[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new IllegalArgumentException("Mismatching dims and array size");
        }
        this.reference = createArrayFromFloatComplex(arr, dims);
    }

    public Array(DoubleComplex[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }
        this.reference = createArrayFromDoubleComplex(arr, dims);
    }

    public Array(boolean[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }
        this.reference = createArrayFromBoolean(arr, dims);
    }

    public Array(short[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }
        this.reference = createArrayFromShort(arr, dims);
    }

    public Array(byte[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }
        this.reference = createArrayFromByte(arr, dims);
    }


    public Array(long[] arr, long[] dims) throws Exception {
        Objects.requireNonNull(arr);
        int totalSize = getNumElements(dims);

        if (arr.length > totalSize || arr.length < totalSize) {
            throw new Exception("Mismatching dims and array size");
        }
        this.reference = createArrayFromLong(arr, dims);
    }

    public Array(long ref) {
        this.reference = ref;
    }

    public Array(Array other) throws Exception {
        this.reference = other.copy().reference;
    }

    private native long createArrayFromDouble(double[] arr, long[] dims) throws Exception;

    private native long createArrayFromFloat(float[] arr, long[] dims) throws Exception;

    private native long createArrayFromBoolean(boolean[] arr, long[] dims) throws Exception;

    private native long createArrayFromInt(int[] arr, long[] dims) throws Exception;

    private native long createArrayFromByte(byte[] arr, long[] dims) throws Exception;

    private native long createArrayFromShort(short[] arr, long[] dims) throws Exception;

    private native long createArrayFromLong(long[] arr, long[] dims) throws Exception;

    private native long createArrayFromFloatComplex(FloatComplex[] arr, long[] dims) throws Exception;

    private native long createArrayFromDoubleComplex(DoubleComplex[] arr, long[] dims) throws Exception;

    private native void deleteArray() throws Exception;

    private native long[] nativeGetDims() throws Exception;

    private native int nativeGetType() throws Exception;

    private native long nativePrint() throws Exception;

    private native DoubleComplex[] getDoubleComplexFromArray() throws Exception;

    private native FloatComplex[] getFloatComplexFromArray() throws Exception;

    private native double[] getDoubleFromArray() throws Exception;

    private native float[] getFloatFromArray() throws Exception;

    private native short[] getShortFromArray() throws Exception;

    private native byte[] getByteFromArray() throws Exception;

    private native boolean[] getBooleanFromArray() throws Exception;

    private native int[] getIntFromArray() throws Exception;

    private native long[] getLongFromArray() throws Exception;

    private native long join(int dim, long refRhs) throws Exception;

    private native long add(long refRhs) throws Exception;

    private native long mul(long refRhs) throws Exception;

    private native long sub(long refRhs) throws Exception;

    private native long div(long refRhs) throws Exception;

    private native long mod(long refRhs) throws Exception;

    private native long pow(long refRhs) throws Exception;

    private native long lt(long refRhs) throws Exception;

    private native long gt(long refRhs) throws Exception;

    private native long le(long refRhs) throws Exception;

    private native long ge(long refRhs) throws Exception;

    private native long eq(long refRhs) throws Exception;

    private native long ne(long refRhs) throws Exception;

    private native long bitAnd(long refRhs) throws Exception;

    private native long bitOr(long refRhs) throws Exception;

    private native long bitXor(long refRhs) throws Exception;

    private native long nativeBitShiftL(int n) throws Exception;

    private native long nativeBitShiftR(int n) throws Exception;

    private native long nativeNot() throws Exception;

    private native long nativeTranspose(boolean conjugate) throws Exception;

    private native long nativeCol(int index) throws Exception;

    private native long nativeCols(int first, int last) throws Exception;

    private native long nativeRow(int index) throws Exception;

    private native long nativeRows(int first, int last) throws Exception;

    private native long matmul(long refRhs) throws Exception;

    private native long nativeCopy() throws Exception;

    private native long as(int type) throws Exception;


    /**
     * Gets the Array reference.
     *
     * @return Reference.
     */
    public long getReference() {
        return reference;
    }

    /**
     * Sets the Array reference.
     *
     * @param ref Reference.
     */
    public void setReference(long ref) {
        this.reference = ref;
    }

    /**
     * Gets the Array type.
     *
     * @return Array data type.
     */
    public Dtype getType() throws Exception {
        return Dtype.values()[nativeGetType()];
    }

    /**
     * Gets the dim4 in order to construct the Array.
     *
     * @param dims The dimensions.
     * @return The dim4.
     * @throws java.lang.Exception When the input parameter is null or the length is greater than 4, because ArrayFire
     *                             supports up to 4 dimension.
     */
    protected static long[] dim4(long[] dims) throws Exception {

        if (dims == null) {
            throw new Exception("Null dimensions object provided");
        }
        else if (dims.length > 4) {
            throw new Exception("ArrayFire supports up to 4 dimensions only");
        }

        long[] arrayDims;
        arrayDims = new long[]{1, 1, 1, 1};
        System.arraycopy(dims, 0, arrayDims, 0, dims.length);

        return arrayDims;
    }

    /**
     * Gets the data stored in the array.
     *
     * @param <Any> The data type to be returned.
     * @return The data to an array of its type.
     */
    public <Any> Any getData() throws Exception {
        switch (getType()) {
            case c32:
                return (Any) getFloatComplexFromArray();
            case f64:
                return (Any) getDoubleFromArray();
            case c64:
                return (Any) getDoubleComplexFromArray();
            case b8:
                return (Any) getBooleanFromArray();
            case s32:
            case u32:
                return (Any) getIntFromArray();
            case u8:
                return (Any) getByteFromArray();
            case s64:
            case u64:
                return (Any) getLongFromArray();
            case s16:
            case u16:
                return (Any) getShortFromArray();
            default:
                return (Any) getFloatFromArray();
        }
    }

    /**
     * Prints the Array.
     */
    public void print() throws Exception {
        nativePrint();
    }

    /**
     * Gets the Array dimensions.
     *
     * @return The dimensions.
     */
    public long[] getDims() throws Exception {
        return nativeGetDims();
    }

    /**
     * Joins this array with the one specified as parameter along the specified dimension.
     *
     * @param dim The dimension along which the join occurs.
     * @param rhs Right-hand side array for the operation.
     * @return The result of joining the given arrays along the specified dimension.
     */
    public Array join(int dim, Array rhs) throws Exception {
        long ref = join(dim, rhs.reference);
        return new Array(ref);
    }

    /**
     * Adds this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The sum of both arrays.
     */
    public Array add(Array rhs) throws Exception {
        long ref = add(rhs.reference);
        return new Array(ref);
    }

    /**
     * Multiplies this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The product of both arrays.
     */
    public Array mul(Array rhs) throws Exception {
        long ref = mul(rhs.reference);
        return new Array(ref);
    }

    /**
     * Subtracts this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The subtraction of both arrays.
     */
    public Array sub(Array rhs) throws Exception {
        long ref = sub(rhs.reference);
        return new Array(ref);
    }

    /**
     * Divides this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The division of both arrays.
     */
    public Array div(Array rhs) throws Exception {
        long ref = div(rhs.reference);
        return new Array(ref);
    }

    /**
     * Performs the modulo operation of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The modulo of this array with the one passed as parameter.
     */
    public Array mod(Array rhs) throws Exception {
        long ref = mod(rhs.reference);
        return new Array(ref);
    }

    /**
     * Powers this array with the one specified as exponent parameter.
     *
     * @param exponent Exponent for the power operation.
     * @return The power of this array with the one passed as parameter.
     */
    public Array pow(Array exponent) throws Exception {
        long ref = pow(exponent.reference);
        return new Array(ref);
    }

    /**
     * Compares (element-wise) if this array is lower than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array lt(Array rhs) throws Exception {
        long ref = lt(rhs.reference);
        return new Array(ref);
    }

    /**
     * Compares (element-wise) if this array is greater than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array gt(Array rhs) throws Exception {
        long ref = gt(rhs.reference);
        return new Array(ref);
    }

    /**
     * Compares (element-wise) if this array is lower or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array le(Array rhs) throws Exception {
        long ref = le(rhs.reference);
        return new Array(ref);
    }

    /**
     * Compares (element-wise) if this array is greater or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array ge(Array rhs) throws Exception {
        long ref = ge(rhs.reference);
        return new Array(ref);
    }

    /**
     * Compares (element-wise) if this array is equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array eq(Array rhs) throws Exception {
        long ref = eq(rhs.reference);
        return new Array(ref);
    }

    /**
     * Compares (element-wise) if this array is not equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    public Array ne(Array rhs) throws Exception {
        long ref = ne(rhs.reference);
        return new Array(ref);
    }

    /**
     * Performs an AND operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an AND operation of this array with the one passed as parameter.
     */
    public Array bitAnd(Array rhs) throws Exception {
        long ref = bitAnd(rhs.reference);
        return new Array(ref);
    }

    /**
     * Performs an OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an OR operation of this array with the one passed as parameter.
     */
    public Array bitOr(Array rhs) throws Exception {
        long ref = bitOr(rhs.reference);
        return new Array(ref);
    }

    /**
     * Performs an eXclusive-OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an eXclusive-OR operation of this array with the one passed as parameter.
     */
    public Array bitXor(Array rhs) throws Exception {
        long ref = bitXor(rhs.reference);
        return new Array(ref);
    }

    /**
     * Performs a left bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a left bit shift operation to this array as many times as specified in the
     * parameter.
     */
    public Array bitShiftL(int n) throws Exception {
        long ref = nativeBitShiftL(n);
        return new Array(ref);
    }

    /**
     * Performs a right bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a right bit shift operation to this array as many times as specified in the
     * parameter.
     */
    public Array bitShiftR(int n) throws Exception {
        long ref = nativeBitShiftR(n);
        return new Array(ref);
    }

    /**
     * Logical NOT operation to this array.
     *
     * @return The result of a logical NOT operation to this array.
     */
    public Array not() throws Exception {
        long ref = nativeNot();
        return new Array(ref);
    }

    /**
     * Transposes this array.
     *
     * @param conjugate If true a conjugate transposition is performed.
     * @return The transposed (conjugate) array.
     */
    public Array transpose(boolean conjugate) throws Exception {
        long ref = nativeTranspose(conjugate);
        return new Array(ref);
    }

    /**
     * Transposes this array without conjugating it.
     *
     * @return The transposed array.
     */
    public Array transpose() throws Exception {
        return transpose(false);
    }

    /**
     * Returns the specified column by index from this array.
     *
     * @param index The column to be retrieved.
     * @return The specified column of this array.
     */
    public Array col(int index) throws Exception {
        long ref = nativeCol(index);
        return new Array(ref);
    }

    /**
     * Retrieves a subset of columns from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of columns to be retrieved.
     * @param last  End of the subset of columns to be retrieved.
     * @return The subset of columns of this array starting at first and finishing at last, both inclusive.
     */
    public Array cols(int first, int last) throws Exception {
        long ref = nativeCols(first, last);
        return new Array(ref);
    }

    /**
     * Retrieves a given row from this array.
     *
     * @param index The row to be retrieved.
     * @return The specified row of this array.
     */
    public Array row(int index) throws Exception {
        long ref = nativeRow(index);
        return new Array(ref);
    }

    /**
     * Retrieves a subset of rows from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of rows to be retrieved.
     * @param last  End of the subset of rows to be retrieved.
     * @return The subset of rows of this array starting at first and finishing at last, both inclusive.
     */
    public Array rows(int first, int last) throws Exception {
        long ref = nativeRows(first, last);
        return new Array(ref);
    }

    /**
     * Performs a matrix multiplication of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of a matrix multiplication of both arrays.
     */
    public Array matmul(Array rhs) throws Exception {
        long ref = matmul(rhs.reference);
        return new Array(ref);
    }

    /**
     * Performs a deep copy of this array. Both the data stored in the device and all the object properties in Java.
     *
     * @return A deep copy of this array.
     */
    public Array copy() throws Exception {
        long ref = nativeCopy();
        return new Array(ref);
    }

    /**
     * Changes the type of this array.
     *
     * @param type Target type of the output array.
     * @return The result of changing the type of the input array to the one passed as parameter.
     */
    public Array as(Dtype type) throws Exception {
        long ref = as(type.ordinal());
        return new Array(ref);
    }

    @Override
    public void close() throws Exception {
        deleteArray();
    }
}
