package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.domain.TeamDomainObject
import com.github.kota.premierNavi.domain.model.MatchDomainModel
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.SplashAnimationView
import com.github.kota.premierNavi.ui.screens.initial.InitialScreen
import com.github.kota.premierNavi.ui.theme.LARGE_PADDING
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
	teamId: RequestState<List<TeamId>>
) {
	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Column (
				modifier = Modifier.padding(bottom = bottomNavigationHeight)
			) {
					if (teamId is RequestState.Failure.EmptyError) {
						InitialScreen(
							rank = rank,
							addTeamId = addTeamId
						)
					} else {
						if (latestGame is ApiResult.ApiSuccess && nextGame is ApiResult.ApiSuccess) {
							Column {
								Text(
									modifier = Modifier
										.padding(LARGE_PADDING),
									fontWeight = FontWeight.Normal,
									fontSize = MaterialTheme.typography.h5.fontSize,
									text = stringResource(id = R.string.latestGame_text)
								)
								HomeDate(
									section = "第${latestGame.data.section}節",
									matchDay = latestGame.data.matchDay
								)
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
								HomeDate(
									section = stringResource(id = R.string.nextGame_text),
									matchDay = nextGame.data.matchDay
								)
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
							SplashAnimationView()
						}
					}
				}
		},
		topBar = {TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}