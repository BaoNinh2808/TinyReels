package com.puskal.inbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.puskal.composable.CustomButton
import com.puskal.composable.TopBar
import com.puskal.core.DestinationRoute.AUTHENTICATION_ROUTE
import com.puskal.theme.R
import com.puskal.theme.SubTextColor
import com.puskal.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboxScreen(navController: NavController) {
    Scaffold(topBar = {
        TopBar(
            navIcon = null,
            title = stringResource(id = R.string.inbox)
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            UnAuthorizedInboxScreen {
                navController.navigate(AUTHENTICATION_ROUTE)
            }
        }
    }
}


@Composable
fun UnAuthorizedInboxScreen(onClickSignup: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_msg),
            contentDescription = null,
            modifier = Modifier.size(68.dp)
        )
        Text(
            text = stringResource(id = R.string.message_and_notifications_will_appear_here),
            color = SubTextColor
        )
        CustomButton(
            buttonText = stringResource(id = R.string.sign_up),
            modifier = Modifier.fillMaxWidth(0.66f)
        ) {
            onClickSignup()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(navController: NavController) {
    var textValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                navIcon = R.drawable.ic_home,
                title = stringResource(id = R.string.create_post)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            DescriptionAndImage(
                textValue = textValue,
                onTextValueChange = { newText ->
                    textValue = newText
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            DisplayTags(navController)

            Spacer(modifier = Modifier.height(10.dp))

            PrivacySetting("Ai được xem video này?")

            Spacer(modifier = Modifier.height(10.dp))

            PrivacySetting("Ai có thể bình luận?")
        }
    }
}

@Composable
fun DescriptionAndImage(
    textValue: String,
    onTextValueChange: (String) -> Unit // Changed the function signature
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        var isTextVisible by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = Color(0xFFD2F3F2),
                    shape = RoundedCornerShape(16.dp) // Set rounded corners for the TextField
                )
                .border(
                    width = 2.dp,
                    color = Color(0xFFAAE9E6),
                    shape = RoundedCornerShape(16.dp) // Set rounded corners for the border
                )
        ) {
            BasicTextField(
                value = textValue,
                onValueChange = { newText ->
                    onTextValueChange(newText) // Use the provided function to update textValue
                    if (newText.isEmpty()) {
                        isTextVisible = true // If value is empty, hide the text
                    } else {
                        isTextVisible = false // If value is not empty, show the text
                    }
                                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp, top = 20.dp)
                    .background(
                        color = Color.Transparent // Make the TextField background transparent
                    ),
                textStyle = Typography.displaySmall,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        content = { innerTextField() }
                    )
                }
            )

            if (isTextVisible) {
                Text(
                    text = "Mô tả nội dung",
                    modifier = Modifier
                        .padding(start = 12.dp, top = 20.dp)
                        .background(Color.Transparent)
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Placeholder for the image and text box overlap
        Box(
            modifier = Modifier
                .weight(1f)
                .height(200.dp)
                .fillMaxWidth()
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp) // Set rounded corners for the Box
                )
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(16.dp) // Set rounded corners for the border
                )
        ) {
            // Image
            val imagePainter = painterResource(id = R.drawable.ic_home)
            Image(
                painter = imagePainter,
                contentDescription = "Loaded Image",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center)
            )

            // Text
            // Box for text with background color
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(0x35D2F3F2),
                        shape = RoundedCornerShape(16.dp) // Set rounded corners for the Box
                    )
            ) {
                Text(
                    text = "Chọn ảnh\nnổi bật",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center),
                    style = Typography.displayMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@Composable
fun DisplayTags(navController: NavController) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClickableTextWithIcon(
            textValue = "Nhắc đến",
            iconId = R.drawable.ic_add,
            onClicked = { navController.navigate(AUTHENTICATION_ROUTE) },
            modifier = Modifier.weight(1f) // Set equal weight for each tag
        )
        ClickableTextWithIcon(
            textValue = "Hashtag",
            iconId = R.drawable.ic_msg,
            onClicked = { navController.navigate(AUTHENTICATION_ROUTE) },
            modifier = Modifier.weight(1f) // Set equal weight for each tag
        )
        ClickableTextWithIcon(
            textValue = "Vị trí",
            iconId = R.drawable.ic_home,
            onClicked = { navController.navigate(AUTHENTICATION_ROUTE) },
            modifier = Modifier.weight(1f) // Set equal weight for each tag
        )
    }
}

@Composable
fun ClickableTextWithIcon(
    textValue: String,
    iconId: Int,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier // Added a default modifier parameter
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClicked() }
            .padding(16.dp)
    ) {
        Text(
            text = textValue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(5.dp)) // Adjust the space between Text and Icon

        IconButton(
            onClick = { onClicked() },
            modifier = Modifier.size(24.dp)
        ) {
            // Your icon here
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Icon",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun PrivacySetting(decribeText : String){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        Text(
            text = decribeText,
            style = Typography.bodyMedium,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.width(10.dp))

        DropdownComponent()
    }

}

@Composable
fun DropdownComponent() {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Mọi người") }
    val items = listOf("Mọi người", "Follower", "Chỉ Mình Tôi")

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Dropdown trigger text
        Text(
            text = selectedItem,
            modifier = Modifier
                .clickable { expanded = true }
                .padding(8.dp)
        )

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedItem = item
                    expanded = false
                }) {
                    Text(text = item) // Display the item's text
                }
            }
        }
    }
}

