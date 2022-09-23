package com.matsuura.facediary.service.common

import org.springframework.web.multipart.MultipartFile

interface S3Service {

    fun uploadImg(file: MultipartFile): String

}