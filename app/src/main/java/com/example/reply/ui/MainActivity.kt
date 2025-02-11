package com.example.reply.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reply.data.LocalEmailsDataProvider
import com.example.reply.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ReplyHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val uiState by viewModel.uiState.collectAsState() // Replace with collectAsStateWithLifecycle() if needed

                Surface(
                    modifier = Modifier, // Ensure the modifier is passed properly
                    tonalElevation = 5.dp,
                    shadowElevation = 3.dp
                ) {
                    ReplyApp(
                        replyHomeUIState = uiState,
                        closeDetailScreen = {
                            viewModel.closeDetailScreen()
                        },
                        navigateToDetail = { emailId ->
                            viewModel.setSelectedEmail(emailId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ReplyAppContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AppTheme {
        Surface(
            modifier = modifier,
            tonalElevation = 5.dp,
            shadowElevation = 3.dp
        ) {
            Column {
                content()
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun ReplyAppPreview() {
    AppTheme {
        Surface(tonalElevation = 5.dp) {
            ReplyApp(
                replyHomeUIState = ReplyHomeUIState(
                    emails = LocalEmailsDataProvider.allEmails
                )
            )
        }
    }
}
