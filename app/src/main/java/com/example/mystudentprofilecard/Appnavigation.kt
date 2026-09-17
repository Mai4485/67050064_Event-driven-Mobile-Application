package com.example.mystudentprofilecard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

// รายการเมนูใน Navigation Drawer
private data class DrawerItem(val route: String, val label: String, val icon: ImageVector)

private val drawerItems = listOf(
    DrawerItem("home", "Home", Icons.Default.Home),
    DrawerItem("profile", "Profile", Icons.Default.Person),
    DrawerItem("register", "Register", Icons.Default.PersonAdd),
    DrawerItem("settings", "Settings", Icons.Default.Settings),
    DrawerItem("about", "About", Icons.Default.Info)
)

// หน้าจอที่โชว์ Bottom Navigation Bar (Registration/About เข้าถึงผ่าน Drawer อย่างเดียว)
private val bottomBarRoutes = setOf("home", "profile", "settings")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot(state: RegistrationState) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: "register"

    val screenTitle = when (currentRoute) {
        "home" -> "Student Hub"
        "profile" -> "My Profile"
        "settings" -> "Settings"
        "about" -> "About"
        else -> "Student Registration"
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(20.dp))
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    ProfileAvatar(photoRes = state.profilePhotoRes, size = 104.dp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        state.studentName.ifBlank { "Student" },
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                    Text(
                        "Computer Science",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = null,
                                modifier = Modifier.size(26.dp)
                            )
                        },
                        label = { Text(item.label, fontSize = 17.sp) },
                        selected = currentRoute == item.route,
                        onClick = {
                            scope.launch { drawerState.close() }
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) { launchSingleTop = true }
                            }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(screenTitle, fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "เมนู", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BrandBlue,
                        titleContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                if (currentRoute in bottomBarRoutes) {
                    NavigationBar {
                        NavigationBarItem(
                            selected = currentRoute == "home",
                            onClick = { navController.navigate("home") { launchSingleTop = true } },
                            icon = { Icon(Icons.Default.Home, contentDescription = null) },
                            label = { Text("Home", fontSize = 13.sp) }
                        )
                        NavigationBarItem(
                            selected = currentRoute == "profile",
                            onClick = { navController.navigate("profile") { launchSingleTop = true } },
                            icon = { Icon(Icons.Default.Person, contentDescription = null) },
                            label = { Text("Profile", fontSize = 13.sp) }
                        )
                        NavigationBarItem(
                            selected = currentRoute == "settings",
                            onClick = { navController.navigate("settings") { launchSingleTop = true } },
                            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                            label = { Text("Settings", fontSize = 13.sp) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "register",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("register") {
                    RegistrationScreen(
                        state = state,
                        onRegistered = {
                            navController.navigate("profile") {
                                popUpTo("register") { inclusive = true }
                            }
                        }
                    )
                }
                composable("profile") {
                    ProfileScreen(
                        state = state,
                        onEditClick = { navController.navigate("register") }
                    )
                }
                composable("settings") {
                    SettingsScreen(
                        state = state,
                        onAboutClick = { navController.navigate("about") }
                    )
                }
                composable("home") {
                    HomeScreen(state = state)
                }
                composable("about") {
                    AboutScreen()
                }
            }
        }
    }
}