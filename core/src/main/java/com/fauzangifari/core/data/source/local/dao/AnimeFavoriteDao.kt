package com.fauzangifari.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fauzangifari.core.data.source.local.entity.AnimeEntity
import com.fauzangifari.core.data.source.local.entity.AnimeGenreCrossRef
import com.fauzangifari.core.data.source.local.entity.GenreEntity

@Dao
interface AnimeFavoriteDao {

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: List<AnimeGenreCrossRef>)

    // Delete
    @Transaction
    @Query("DELETE FROM anime_favorite WHERE mal_id = :malId")
    suspend fun deleteAnime(malId: Int)

    @Transaction
    @Query("DELETE FROM anime_genre_cross_ref WHERE animeMalId = :malId")
    suspend fun deleteCrossRef(malId: Int)

    // Select
    @Transaction
    @Query("SELECT * FROM anime")
    suspend fun getAllAnime(): List<AnimeEntity>

}