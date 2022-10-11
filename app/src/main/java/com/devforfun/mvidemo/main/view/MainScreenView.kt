package com.devforfun.mvidemo.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devforfun.mvidemo.R
import com.devforfun.mvidemo.main.model.models.Car
import com.devforfun.mvidemo.main.viewmodel.MainNavigation
import com.devforfun.mvidemo.main.viewmodel.MainViewModel
import com.devforfun.mvidemo.main.viewmodel.NextActionClick
import com.devforfun.mvidemo.main.viewmodel.SelectedCarActionClick
import com.devforfun.mvidemo.ui.components.BottomAppBarView
import com.devforfun.mvidemo.ui.components.EmojiCardHint
import com.devforfun.mvidemo.ui.components.SectionHeaderView
import com.devforfun.mvidemo.ui.components.SelectableCardItem
import com.devforfun.mvidemo.ui.components.TopBar
import com.devforfun.mvidemo.ui.theme.BottomSheetShape
import com.devforfun.mvidemo.ui.theme.MVIDemoTheme
import kotlinx.coroutines.launch

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
            BottomSheetContent(
                title = "Select the car you want to buy",
                carList = viewState.value.mainModel.cars,
                onSelectedCar = {
                    viewModel.onAction(SelectedCarActionClick(it))
                    coroutineScope.launch { modalBottomSheetState.hide() }
                }
            )
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
                        onSelectActionClick = {
                            coroutineScope.launch { modalBottomSheetState.show() }
                        }
                    )
                }
            )
        }
    }

    LaunchedEffect(null) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                NavigateToPersonalData -> {
                    appNavigator.navigateToPersonalDataScreen()
                }
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    viewModel: MainViewModel,
    onSelectActionClick: () -> Unit
) {
    val viewState = viewModel.container.stateFlow.collectAsState()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmojiCardHint(
            title = stringResource(R.string.emoji_hint)
        )
        Spacer(modifier = Modifier.height(MVIDemoTheme.dimens.grid_2))
        SectionHeaderView(
            modifier = Modifier,
            title = stringResource(R.string.header_title_buy_car),
            buttonText = stringResource(R.string.select),
            onButtonClick = { onSelectActionClick() })

        Spacer(modifier = Modifier.height(MVIDemoTheme.dimens.grid_1_5))

        var title = "Please add a car"
        var subtitle = "No car added"
        var emoji = "\uD83D\uDE4A"

        val selectedCar = viewState.value.mainModel.selectedCar

        selectedCar?.let {
            title = selectedCar.title
            subtitle = ""
            emoji = selectedCar.emoji
        }
        SelectableCardItem(
            modifier = Modifier,
            title = title,
            subtitle = subtitle,
            emoji = emoji,
        )
        selectedCar?.let {
            Spacer(modifier = Modifier.height(MVIDemoTheme.dimens.grid_1_5))
            EmojiCardHint(
                title = stringResource(R.string.emoji_hint_cool),
            )
        }
    }
}

@Composable
internal fun BottomSheetContent(
    modifier: Modifier = Modifier,
    title: String,
    carList: List<Car>,
    onSelectedCar: (Car) -> Unit
) {
    MVIDemoTheme {
        Column(
            modifier = modifier.fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.padding(MVIDemoTheme.dimens.grid_1))
            ModalBottomLine()
            Spacer(modifier = Modifier.height(MVIDemoTheme.dimens.grid_2))
            Text(
                text = title,
                color = MVIDemoTheme.colors.purple,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MVIDemoTheme.dimens.grid_1_5),
            )
            Spacer(modifier = Modifier.height(MVIDemoTheme.dimens.grid_2))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MVIDemoTheme.colors.background),
            ) {

                item {
                    Spacer(modifier = Modifier.height(MVIDemoTheme.dimens.grid_1_5))
                    Text(
                        modifier = Modifier.padding(
                            start = MVIDemoTheme.dimens.grid_1_5,
                            end = MVIDemoTheme.dimens.grid_1_5
                        ),
                        text = stringResource(R.string.available_cars),
                        style = MaterialTheme.typography.body1
                    )
                }

                items(carList) {
                    Spacer(modifier = Modifier.height(MVIDemoTheme.dimens.grid_1_5))
                    SelectableCardItem(
                        modifier = Modifier.padding(
                            start = MVIDemoTheme.dimens.grid_1_5,
                            end = MVIDemoTheme.dimens.grid_1_5
                        ),
                        title = it.title,
                        emoji = it.emoji,
                        onItemClick = { onSelectedCar(it) },
                    )
                }
            }
        }
    }
}

@Composable
fun ModalBottomLine(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Divider(
            thickness = MVIDemoTheme.dimens.custom_0_4,
            modifier = Modifier
                .width(MVIDemoTheme.dimens.grid_5)
                .clip(RoundedCornerShape(MVIDemoTheme.dimens.grid_2))
        )
    }
}
