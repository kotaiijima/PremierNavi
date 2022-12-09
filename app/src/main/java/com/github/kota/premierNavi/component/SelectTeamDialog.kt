package com.github.kota.premierNavi.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SelectTeamDialog(
	title: String,
	message: String = "",
	openDialog: Boolean,
	closeDialog: () -> Unit,
	onYesClicked: () -> Unit
) {
	if (openDialog) {
		AlertDialog(
			title = {
				Text(
					text = title,
					fontSize = MaterialTheme.typography.h5.fontSize,
					fontWeight = FontWeight.Bold
				)
			},
			text = {
				Text(
					text = message,
					fontSize = MaterialTheme.typography.subtitle2.fontSize,
					fontWeight = FontWeight.Normal
				)
			},
			confirmButton = {
				Button(
					onClick = {
						onYesClicked()
						closeDialog()
					}
				) {
					Text(text = "はい")
				}
			},
			dismissButton = {
				OutlinedButton(
					onClick = {
						closeDialog()
					}
				) {
					Text(text = "いいえ")
				}
			},
			onDismissRequest = { closeDialog() }
		)
	}
}

@Composable
@Preview
fun SelectTeamDialogPreview() {
	SelectTeamDialog(
		title = "Title",
		message = "Message",
		openDialog = true,
		closeDialog = { /*TODO*/ }) {
	}
}