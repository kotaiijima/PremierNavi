package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.data.databaseModel.TeamId
import com.github.kota.premierNavi.domain.TeamDomainObject
import com.github.kota.premierNavi.domain.model.MatchDomainModel
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import com.github.kota.premierNavi.utils.showCrest

@Composable
fun HomeScreen(
	navigateToTeamDetail:(Int) -> Unit,
	navController: NavController,
	latestGame: ApiResult<MatchDomainModel>,
	nextGame: ApiResult<MatchDomainModel>,
	teamId: RequestState<List<TeamId>>,
	getMatchData: suspend (Int) -> Unit
) {
	val scaffoldState = rememberScaffoldState()

	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			if (latestGame is ApiResult.ApiSuccess && nextGame is ApiResult.ApiSuccess) {
				Column(
					modifier = Modifier.padding(bottom = bottomNavigationHeight)
				) {
					Column {
						HomeDate(
							matchStatus = stringResource(id = R.string.latestGame_text),
							competition = latestGame.data.competition,
							section = latestGame.data.section,
							round = latestGame.data.round,
							matchDay = latestGame.data.matchDay
						)
						Divider()
						HomeLatestGame(
							matchResult = "${latestGame.data.score.homeScore} - ${latestGame.data.score.awayScore}",
							homeTeam = TeamDomainObject(
								id = latestGame.data.homeTeam.id,
								name = latestGame.data.homeTeam.name,
								crest = showCrest(
									crest = latestGame.data.homeTeam.crest
								)
							),
							awayTeam = TeamDomainObject(
								id = latestGame.data.awayTeam.id,
								name = latestGame.data.awayTeam.name,
								crest = showCrest(
									crest = latestGame.data.awayTeam.crest
								)
							),
							navigateToTeamDetail = navigateToTeamDetail
						)
						Divider()
						HomeDate(
							matchStatus = stringResource(id = R.string.nextGame_text),
							competition = nextGame.data.competition,
							section = nextGame.data.section,
							round = nextGame.data.round,
							matchDay = nextGame.data.matchDay
						)
						Divider()
						HomeNextGame(
							homeTeam = TeamDomainObject(
								id = nextGame.data.homeTeam.id,
								name = nextGame.data.homeTeam.name,
								crest = showCrest(crest = nextGame.data.homeTeam.crest)
							),
							awayTeam = TeamDomainObject(
								id = nextGame.data.awayTeam.id,
								name = nextGame.data.awayTeam.name,
								crest = showCrest(crest = nextGame.data.awayTeam.crest)
							),
							navigateToTeamDetail = navigateToTeamDetail
						)
					}
				}
			}else {
				if (teamId is RequestState.Success) {
					LoadingAnimationView(
						getApiData = getMatchData,
						teamId = teamId.data[0].teamId
					)
				}
			}
		},
		topBar = { TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}