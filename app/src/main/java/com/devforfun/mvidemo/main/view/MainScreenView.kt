package com.devforfun.mvidemo.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devforfun.mvidemo.R
import com.devforfun.mvidemo.main.viewmodel.MainNavigation
import com.devforfun.mvidemo.main.viewmodel.MainViewModel
import com.devforfun.mvidemo.main.viewmodel.NextActionClick
import com.devforfun.mvidemo.ui.components.BottomAppBarView
import com.devforfun.mvidemo.ui.components.EmojiCardHint
import com.devforfun.mvidemo.ui.components.TopBar
import com.devforfun.mvidemo.ui.theme.BottomSheetShape
import com.devforfun.mvidemo.ui.theme.MVIDemoTheme
import com.devforfun.mvidemo.utils.throttleFirst

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreenView(
    appNavigator: MainNavigation,
    viewModel: MainViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val viewState = viewModel.container.stateFlow.collectAsState()

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = BottomSheetShape,
        sheetContent = {
            //this is added because of the crash https://github.com/google/accompanist/issues/910
            Spacer(modifier = Modifier.height(1.dp))
        }) {
        MVIDemoTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { TopBar(title = stringResource(R.string.main_screen_title)) },
                bottomBar = {
                    BottomAppBarView(
                        onBackScreen = {},
                        onNextScreen = { viewModel.onAction(NextActionClick) },
                        imageVectorNext = Icons.Filled.ArrowForward
                    )
                },
                content = { padding ->
                    MainContent(
                        modifier = Modifier
                            .background(MVIDemoTheme.colors.background)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(
                                top = MVIDemoTheme.dimens.grid_1_5,
                                start = MVIDemoTheme.dimens.grid_1_5,
                                end = MVIDemoTheme.dimens.grid_1_5,
                                bottom = padding.calculateBottomPadding()
                            ),
                        viewModel = viewModel,
                    )
                }
            )
        }
    }

    LaunchedEffect(null) {
        viewModel.container.sideEffectFlow.throttleFirst(1000L).collect {
            when (it) {
                NavigateNext -> {
                    appNavigator.moveToFormsScreen()
                }
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    viewModel: MainViewModel
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmojiCardHint(
            modifier = Modifier,
            title = stringResource(R.string.emoji_hint)
        )
        Spacer(modifier = Modifier.height(MVIDemoTheme.dimens.grid_2))
        //todo add a lazy column with some items
        //open new screen when selecting an item
        //edit item with bottom navigation

    }
}
