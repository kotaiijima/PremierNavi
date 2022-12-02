package com.github.kota.premierNavi.usecase

import com.github.kota.premierNavi.data.api.model.teamModel.ApiTeam
import com.github.kota.premierNavi.data.api.model.teamModel.Squad
import com.github.kota.premierNavi.domain.FootballDataRepository
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.Coach
import com.github.kota.premierNavi.domain.model.Player
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import javax.inject.Inject

class TeamUseCase @Inject constructor(
  private val teamApiService: TeamApiService
  ) {
  suspend operator fun invoke(teamId: TeamIdDomainObject):  RequestState<TeamDomainModel>{
	  val apiTeam =  teamApiService.convertTeam(teamId)

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
