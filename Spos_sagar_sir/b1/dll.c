#include<jni.h>
#include<stdio.h>
#include "dll.h"

JNIEXPORT int JNICALL Java_dll_add (JNIEnv *env,jobject jobj,jint a,jint b)
{
printf("%d + %d = %d",a,b,(a+b));
return;
}
