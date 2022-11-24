package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.domain.TeamDomainObject
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.SplashAnimationView
import com.github.kota.premierNavi.ui.screens.initial.InitialScreen
import com.github.kota.premierNavi.ui.theme.LARGE_PADDING
import com.github.kota.premierNavi.ui.theme.SMALL_PADDING
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import com.github.kota.premierNavi.utils.showCrest
import kotlin.text.Typography.section

@Composable
fun HomeScreen(
	navigateToTeamDetail:(Int) -> Unit,
	navController: NavController,
	addTeamId: (Int) -> Unit,
	latestGame: ApiResult<Match>,
	nextGame: ApiResult<Match>,
	rank: ApiResult<Rank>,
	teamId: RequestState<List<TeamId>>
) {
	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Column (
				modifier = Modifier.padding(bottom = bottomNavigationHeight)
			) {
					if (teamId is RequestState.Empty) {
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
									section = "第${latestGame.data.matches[0].season.currentMatchday}節",
									datetime = latestGame.data.matches[0].utcDate
								)
								HomeLatestGame(
									matchResult = "${latestGame.data.matches[0].score.fullTime?.home} - ${latestGame.data.matches[0].score.fullTime?.away}",
									homeTeam = TeamDomainObject(
										id = latestGame.data.matches[0].homeTeam.id,
										name = latestGame.data.matches[0].homeTeam.shortName,
										crest = showCrest(crest = latestGame.data.matches[0].homeTeam.crest)
									),
									awayTeam = TeamDomainObject(
										id = latestGame.data.matches[0].awayTeam.id,
										name = latestGame.data.matches[0].awayTeam.shortName,
										crest = showCrest(crest = latestGame.data.matches[0].awayTeam.crest)
									),
									navigateToTeamDetail = navigateToTeamDetail
								)
								HomeDate(
									section = stringResource(id = R.string.nextGame_text),
									datetime = nextGame.data.matches[0].utcDate
								)
								HomeNextGame(
									homeTeam = TeamDomainObject(
										id = nextGame.data.matches[0].homeTeam.id,
										name = nextGame.data.matches[0].homeTeam.shortName,
										crest = showCrest(crest = nextGame.data.matches[0].homeTeam.crest)
									),
									awayTeam = TeamDomainObject(
										id = nextGame.data.matches[0].awayTeam.id,
										name = nextGame.data.matches[0].awayTeam.shortName,
										crest = showCrest(crest = nextGame.data.matches[0].awayTeam.crest)
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