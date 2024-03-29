package com.metehan.authentication.presentation.menu_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.metehan.authentication.domain.models.Merchant
import com.metehan.authentication.domain.models.MerchantList
import com.metehan.authentication.presentation.menu_screen.components.MenuAction
import com.metehan.authentication.presentation.menu_screen.components.MenuSearchBar
import com.metehan.authentication.presentation.menu_screen.components.MerchantList
import com.metehan.authentication.util.SharedViewModel


@Composable
fun MenuScreen(
    navController: NavController,
    menuViewModel: MenuViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
//    val sharedViewModel: SharedViewModel = viewModel()
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MenuSearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 4.dp, top = 4.dp)
            ) {
                menuViewModel.searchMenuList(it)
            }
            LaunchedEffect(menuViewModel.merchantList.value) {
                menuViewModel.merchantList.value?.let {
                    if (it.isNotEmpty()) {
                        val merchants = MerchantList(it)
                        println("merchants: $merchants")
                        sharedViewModel.setMerchantList(merchants)
                    }
                }
            }
            MenuAction(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                navController
            )
            Spacer(modifier = Modifier.height(8.dp))
            MerchantList(navController = navController)
        }
    }
}