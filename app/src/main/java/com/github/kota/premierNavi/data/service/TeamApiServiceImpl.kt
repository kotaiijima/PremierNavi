package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.model.teamModel.ApiTeam
import com.github.kota.premierNavi.data.api.model.teamModel.Squad
import com.github.kota.premierNavi.domain.model.Coach
import com.github.kota.premierNavi.domain.model.Player
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import javax.inject.Inject

class TeamApiServiceImpl @Inject constructor() : TeamApiService {
  override suspend fun convertTeam(apiTeam: ApiResult<ApiTeam>): RequestState<TeamDomainModel> {

    if (apiTeam is ApiResult.ApiSuccess){
        return RequestState.Success(
            TeamDomainModel(
                crest = apiTeam.data.crest,
                teamName = apiTeam.data.name,
                stadium = apiTeam.data.venue,
                squad = listPlayers(apiTeam.data.squad),
                coach = Coach(
                    dateOfBirth = apiTeam.data.coach.dateOfBirth,
                    coachName = apiTeam.data.coach.name,
                    nationality = apiTeam.data.coach.nationality
                )
            ))
    }else {
        return RequestState.Empty
    }
  }
}

private fun listPlayers(
    squad: List<Squad>
) : List<Player> {
    val players = mutableListOf<Player>()
    for (player in squad) {
        players.add(
            Player(
                dateOfBirth = player.dateOfBirth,
                playerName = player.name,
                nationality = player.nationality,
                position = player.position
            )
        )
    }
    return players
}
