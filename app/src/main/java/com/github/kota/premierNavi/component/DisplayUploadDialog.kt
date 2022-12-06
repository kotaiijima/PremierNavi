package com.github.kota.premierNavi.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.github.kota.premierNavi.R

@Composable
fun DisplayUploadDialog(
	message: String,
	openDialog: Boolean,
	closeDialog: () -> Unit,
	onYesClicked: () -> Unit
){
	if (openDialog){
		AlertDialog(
			title = {
				Text(
					text = stringResource(id = R.string.confirm_upload),
					fontSize = MaterialTheme.typography.h5.fontSize,
					fontWeight = FontWeight.Bold
				)
			},
			text = {
				Text(
					text = message,
					fontSize = MaterialTheme.typography.subtitle1.fontSize,
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