package com.amotrade.trendtenhub.utils

import com.amotrade.trendtenhub.data.models.BuiltByData
import com.amotrade.trendtenhub.data.models.GithubRepositoryData
import com.amotrade.trendtenhub.data.models.schemas.BuiltBySchema
import com.amotrade.trendtenhub.data.models.schemas.GithubRepositorySchema
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers
import org.junit.Test

class MappingUtilsKtTest {

    @Test
    fun getTrendingGithubRepositoryDataList_schemaList_returnsData() {
        assertThat(getTrendingGithubRepositoryDataList(getGithubRepositoriesSchemaList()),
            CoreMatchers.`is`(getGithubRepositoryDataList()))
    }

    @Test
    fun getTrendingGithubRepositoryDataList_schemaListEmpty_returnsEmptyDataList(){
        assertThat(getTrendingGithubRepositoryDataList(emptyList()),CoreMatchers.`is`(emptyList()))
    }

    private fun getGithubRepositoriesSchemaList(): List<GithubRepositorySchema> {
        val repositories = listOf<GithubRepositorySchema>(
            GithubRepositorySchema(
                "xingshaocheng",
                "architect-awesome",
                "https://github.com/xingshaocheng.png",
                "https://github.com/xingshaocheng.png",
                "后端架构师技术图谱",
                "Go",
                "#3572A5",
                12,
                13,
                21,
                getBuiltBySchemaList()
            )
        )
        return repositories
    }

    private fun getBuiltBySchemaList(): List<BuiltBySchema> {
        return listOf(
            BuiltBySchema(
                "viatsko",
                "https://github.com/viatsko",
                "https://github.com/viatsko"
            )
        )
    }

    private fun getGithubRepositoryDataList(): List<GithubRepositoryData> {
        val repositoriesData = listOf<GithubRepositoryData>(
            GithubRepositoryData(
                "xingshaocheng",
                "architect-awesome",
                "https://github.com/xingshaocheng.png",
                "https://github.com/xingshaocheng.png",
                "后端架构师技术图谱",
                "Go",
                "#3572A5",
                12,
                13,
                21,
                getBuiltByDataList()
            )
        )
        return repositoriesData
    }

    private fun getBuiltByDataList(): List<BuiltByData> {
        return listOf(
            BuiltByData(
                "viatsko",
                "https://github.com/viatsko",
                "https://github.com/viatsko"
            )
        )
    }

}