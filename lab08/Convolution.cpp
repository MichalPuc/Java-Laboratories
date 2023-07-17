#include "Convolution.h"

JNIEXPORT jobjectArray JNICALL convolutionNative(JNIEnv* env, jclass clazz, jobjectArray jkernel, jobjectArray jinput) 
{
    // get the dimensions of the kernel and input matrices
    jsize kernelRows = env->GetArrayLength(jkernel);
    jobjectArray firstRow = (jobjectArray)env->GetObjectArrayElement(jkernel, 0);
    jsize kernelCols = env->GetArrayLength(firstRow);
    jsize inputRows = env->GetArrayLength(jinput);
    firstRow = (jobjectArray)env->GetObjectArrayElement(jinput, 0);
    jsize inputCols = env->GetArrayLength(firstRow);

    // allocate memory for the kernel and input matrices
    int** kernel = new int* [kernelRows];
    for (int i = 0; i < kernelRows; i++) 
    {
        kernel[i] = new int[kernelCols];
        jintArray row = (jintArray)env->GetObjectArrayElement(jkernel, i);
        jint* rowPtr = env->GetIntArrayElements(row, NULL);
        for (int j = 0; j < kernelCols; j++) 
        {
            kernel[i][j] = rowPtr[j];
        }
        env->ReleaseIntArrayElements(row, rowPtr, 0);
    }

    int** input = new int* [inputRows];
    for (int i = 0; i < inputRows; i++) 
    {
        input[i] = new int[inputCols];
        jintArray row = (jintArray)env->GetObjectArrayElement(jinput, i);
        jint* rowPtr = env->GetIntArrayElements(row, NULL);
        for (int j = 0; j < inputCols; j++) {
            input[i][j] = rowPtr[j];
        }
        env->ReleaseIntArrayElements(row, rowPtr, 0);
    }

    // perform the convolution operation
    int** result = new int* [inputRows];
    for (int i = 0; i < inputRows; i++) 
    {
        result[i] = new int[inputCols];
        for (int j = 0; j < inputCols; j++) 
        {
            result[i][j] = 0;
            for (int k = 0; k < kernelRows; k++) 
            {
                for (int l = 0; l < kernelCols; l++) 
                {
                    int row = i - k + kernelRows / 2;
                    int col = j - l + kernelCols / 2;
                    if (row >= 0 && row < inputRows && col >= 0 && col < inputCols) {
                        result[i][j] += kernel[k][l] * input[row][col];
                    }
                }
            }
        }
    }

    // create the result array
    jobjectArray jresult = env->NewObjectArray(inputRows, env->FindClass("[I"), NULL);
    for (int i = 0; i < inputRows; i++) 
    {
        jintArray row = env->NewIntArray(inputCols);
        env->SetIntArrayRegion(row, 0, inputCols, reinterpret_cast<const jint*>(result[i]));
        env->SetObjectArrayElement(jresult, i, row);
        env->DeleteLocalRef(row);
    }

    // free the memory allocated for the matrices
    for (int i = 0; i < kernelRows; i++) 
    {
        delete[] kernel[i];
    }
    delete[] kernel;

    for (int i = 0; i < inputRows; i++) 
    {
        delete[] input[i];
    }
    delete[] input;

    for (int i = 0; i < inputRows; i++) 
    {
        delete[] result[i];
    }
    delete[] result;

    // return the result array
    return jresult;
}

int main()
{

}