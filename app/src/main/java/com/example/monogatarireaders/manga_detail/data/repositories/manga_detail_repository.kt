package com.example.monogatarireaders.manga_detail.data.repositories

import com.example.monogatarireaders.R
import com.example.monogatarireaders.manga_detail.domain.adapters.ChapterAdapter
import com.example.monogatarireaders.manga_detail.domain.adapters.MangaDetailAdapter

class MangaDetailRepository {
    // Mock data for manga chapters
    private fun getChaptersForManga(mangaId: Int): List<ChapterAdapter> {
        return when (mangaId) {
            2 -> {
                listOf(
                    ChapterAdapter(id = 215, title = "Episode 215", date = "8 January 2025", views = 3400),
                    ChapterAdapter(id = 214, title = "Episode 214", date = "1 January 2025", views = 3200),
                    ChapterAdapter(id = 213, title = "Episode 213", date = "25 December 2024", views = 3500),
                    ChapterAdapter(id = 212, title = "Episode 212", date = "18 December 2024", views = 3300),
                    ChapterAdapter(id = 211, title = "Episode 211", date = "11 December 2024", views = 3450)
                )
            }
            1 -> {
                listOf(
                    ChapterAdapter(id = 150, title = "Episode 150", date = "6 January 2025", views = 4200),
                    ChapterAdapter(id = 149, title = "Episode 149", date = "30 December 2024", views = 4100),
                    ChapterAdapter(id = 148, title = "Episode 148", date = "23 December 2024", views = 3900),
                    ChapterAdapter(id = 147, title = "Episode 147", date = "16 December 2024", views = 4050),
                    ChapterAdapter(id = 146, title = "Episode 146", date = "9 December 2024", views = 3950)
                )
            }
            else -> {
                listOf(
                    ChapterAdapter(id = 1, title = "Episode 1", date = "5 January 2025", views = 3100),
                    ChapterAdapter(id = 2, title = "Episode 2", date = "12 January 2025", views = 2900),
                    ChapterAdapter(id = 3, title = "Episode 3", date = "19 January 2025", views = 2800),
                    ChapterAdapter(id = 4, title = "Episode 4", date = "26 January 2025", views = 2750)
                )
            }
        }
    }

    // Mock data for manga details
    private val mangaDetailsMap = mapOf(
        1 to MangaDetailAdapter(
            id = 1,
            title = "Chainsaw Man",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Tatsuki Fujimoto",
            genres = listOf("Action", "Horror", "Supernatural"),
            rating = 4.8f,
            status = "Ongoing",
            synopsis = "Denji is a young boy who works as a Devil Hunter with the 'Chainsaw Devil' Pochita. One day, as he was living his miserable life trying to pay off his debt, he gets betrayed and killed. As he was dying, he makes a contract with Pochita and gets revived as the 'Chainsaw Man'.",
            chapters = getChaptersForManga(1),
            isTrending = true
        ),
        2 to MangaDetailAdapter(
            id = 2,
            title = "Jujutsu Kaisen",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Gege Akutami",
            genres = listOf("Action", "Fantasy", "Horror"),
            rating = 4.8f,
            status = "Completed",
            synopsis = "Yuji Itadori never expected to get involved in the world of curses and sorcery. But after swallowing a powerful cursed object, his life changes forever. Join Yuji and his friends in a fight against curses and destiny itself.",
            chapters = getChaptersForManga(2),
            isTrending = true
        ),
        3 to MangaDetailAdapter(
            id = 3,
            title = "Demon Slayer",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Koyoharu Gotouge",
            genres = listOf("Action", "Supernatural", "Historical"),
            rating = 4.9f,
            status = "Completed",
            synopsis = "In Taisho-era Japan, Tanjiro Kamado is a kindhearted boy who makes a living selling charcoal. But his peaceful life is shattered when a demon slaughters his entire family. His little sister Nezuko is the only survivor, but she has been transformed into a demon herself!",
            chapters = getChaptersForManga(3),
            isTrending = true
        ),
        4 to MangaDetailAdapter(
            id = 4,
            title = "One Piece",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Eiichiro Oda",
            genres = listOf("Action", "Adventure", "Fantasy"),
            rating = 4.9f,
            status = "Ongoing",
            synopsis = "As a child, Monkey D. Luffy was inspired to become a pirate by listening to the tales of the buccaneer 'Red-Haired' Shanks. But Luffy's life changed when he accidentally ate the Gum-Gum Devil Fruit and gained the power to stretch like rubber at the cost of never being able to swim again.",
            chapters = getChaptersForManga(4),
            isTrending = true
        ),
        5 to MangaDetailAdapter(
            id = 5,
            title = "My Hero Academia",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Kohei Horikoshi",
            genres = listOf("Action", "Superhero", "School"),
            rating = 4.6f,
            status = "Ongoing",
            synopsis = "In a world where people with superpowers (known as 'Quirks') are the norm, Izuku Midoriya has dreams of one day becoming a Hero, despite being bullied by his classmates for not having a Quirk. After being the only one to try and save his childhood bully from a villain, All Might, the world's greatest Hero, bestows upon him his own Quirk.",
            chapters = getChaptersForManga(5),
            isTrending = true
        ),
        6 to MangaDetailAdapter(
            id = 6,
            title = "Tokyo Revengers",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Ken Wakui",
            genres = listOf("Action", "Drama", "Time Travel"),
            rating = 4.5f,
            status = "Completed",
            synopsis = "Takemichi Hanagaki is a freelancer that's reached the absolute pits of despair in his life. He finds out that the only girlfriend he ever had, in middle school, Hinata Tachibana, had been killed by the Tokyo Manji Gang. The day after hearing about her death, he's standing on the station platform and ends up being pushed over onto the tracks by a herd of people.",
            chapters = getChaptersForManga(6),
            isTrending = true
        )
    )

    fun getMangaDetailsById(id: Int): MangaDetailAdapter {
        return mangaDetailsMap[id] ?: mangaDetailsMap[1]!!
    }
}