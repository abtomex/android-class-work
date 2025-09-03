package dom.dima.learn.classworksprint_15.data

import dom.dima.learn.classworksprint_15.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}