#pragma once 
#include <jni.h>
#include "C:\Program Files\Java\jdk-17\include\jni.h"


#ifdef __cplusplus
extern "C" {
#endif
    JNIEXPORT jobjectArray JNICALL convolutionNative(JNIEnv* env, jclass clazz, jobjectArray jkernel, jobjectArray jinput);
        
#ifdef __cplusplus
}
#endif
