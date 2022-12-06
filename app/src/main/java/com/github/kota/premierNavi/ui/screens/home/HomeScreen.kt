package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.domain.TeamDomainObject
import com.github.kota.premierNavi.domain.model.MatchDomainModel
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.screens.animation.SplashAnimationView
import com.github.kota.premierNavi.ui.screens.initial.InitialScreen
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import com.github.kota.premierNavi.utils.showCrest

@Composable
fun HomeScreen(
	navigateToTeamDetail:(Int) -> Unit,
	navController: NavController,
	addTeamId: (Int) -> Unit,
	latestGame: ApiResult<MatchDomainModel>,
	nextGame: ApiResult<MatchDomainModel>,
	rank: ApiResult<RankDomainModel>,
	teamId: RequestState<List<TeamId>>,
	getMatchData: (Int) -> Unit
) {
	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Column (
				modifier = Modifier.padding(bottom = bottomNavigationHeight)
			) {
					if (teamId is RequestState.Failure.Error) {
						InitialScreen(
							rank = rank,
							addTeamId = addTeamId
						)
					} else {
						if (latestGame is ApiResult.ApiSuccess && nextGame is ApiResult.ApiSuccess) {
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
										crest = showCrest(crest = latestGame.data.homeTeam.crest
										)
									),
									awayTeam = TeamDomainObject(
										id = latestGame.data.awayTeam.id,
										name = latestGame.data.awayTeam.name,
										crest = showCrest(crest = latestGame.data.awayTeam.crest
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
						} else {
							if (teamId is RequestState.Success){
								LoadingAnimationView(
									getApiData = getMatchData,
									teamId = teamId.data[0].teamId
								)
							}
						}
					}
				}
		},
		topBar = {TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}