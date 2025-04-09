package com.monogatari.app.manga_chapter.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.manga_chapter.data.repositories.UpdateProgressLectureRepository
import com.monogatari.app.manga_detail.domain.dtos.UpdateProgressLectureDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateProgressLectureService {
    private val _updateProgressLectureRepository = RetroFitClient.createService(
        UpdateProgressLectureRepository::class.java)

    suspend fun updateProgress(updateProgressLectureDto: UpdateProgressLectureDto): Result<String> {
        return withContext(Dispatchers.IO){
            try {
                val response = _updateProgressLectureRepository.updateProgressLecture(updateProgressLectureDto)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success("Manga progress updated successfully")
                    } else if(response.code() == 500) {
                        Result.success("Manga progress updated successfully")
                    }else {
                        Result.failure(Exception("Empty response body"))
                    }
                }else{
                    val resError = "Error: ${response.code()} - ${response.message()}"
                    Result.failure(Exception(resError))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}