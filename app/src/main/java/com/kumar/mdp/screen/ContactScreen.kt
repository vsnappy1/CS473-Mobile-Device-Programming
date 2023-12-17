package com.kumar.mdp.screen

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kumar.mdp.R


@Composable
fun ContactScreen() {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("Contact Us", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Got any question? contact us via phone, email or visit us and we would be very happy to assist you.",
                modifier= Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(20.dp))
            ContactCard(
                res = R.drawable.round_local_phone_24,
                text = "+1 641 111 333"
            ) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + "+1 641 111 333")
                context.startActivity(intent)
            }
            ContactCard(
                res = R.drawable.round_email_24,
                text = "cheif@recipe.com"
            ) {
                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.setData(Uri.parse("mailto:"))
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("cheif@recipe.com"))
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Customer Query")
                context.startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            }
            ContactCard(
                res = R.drawable.round_map_24,
                text = "1000 N 4th St, Fairfield, Iowa"
            ) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=Maharishi International University, Fairfield, USA")
                )
                context.startActivity(intent)
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactCard(
    @DrawableRes res: Int,
    text: String,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier.width(250.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, color = Color.DarkGray),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = res),
                contentDescription = ""
            )
            Text(text = text, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Preview
@Composable
fun PreviewContactScreen() {
    ContactScreen()
}