package com.fauzangifari.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fauzangifari.core.data.source.local.entity.AnimeEntity
import com.fauzangifari.core.data.source.local.entity.AnimeGenreCrossRef
import com.fauzangifari.core.data.source.local.entity.GenreEntity
import com.fauzangifari.core.data.source.local.relation.AnimeWithGenres

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
    @Query("DELETE FROM anime WHERE malId = :malId")
    suspend fun deleteAnime(malId: Int)

    @Transaction
    @Query("DELETE FROM anime_genre_cross_ref WHERE animeId = :malId")
    suspend fun deleteCrossRef(malId: Int)

    // Select
    @Transaction
    @Query("SELECT * FROM anime")
    suspend fun getAllAnimeWithGenres(): List<AnimeWithGenres>

    @Transaction
    @Query("SELECT EXISTS(SELECT * FROM anime WHERE malId = :malId)")
    suspend fun isAnimeFavorite(malId: Int): Boolean

}