package com.example.filmlistan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmlistan.ui.theme.FilmlistanTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale







class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilmlistanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        FilmLista()
                    }
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FilmlistanTheme {
        Greeting("Android")
    }
}


@Composable
fun FilmLista(viewModel: FilmlistanViewModel = viewModel() ) {
    Column {
        Text(
            text = "Min fantastiska filmlista",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6750A4))
                .padding(20.dp),
            color = Color.White
        )
        OutlinedTextField(
            value = viewModel.uiState.nyTitel,
            onValueChange = { nyText ->
                viewModel.uppdateraTitel(nyText)
            },
            label = { Text("Ny filmtitel") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Button(
            onClick = { viewModel.laggTillFilm() },
            enabled = viewModel.uiState.nyTitel.isNotBlank(),
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Lägg till")
        }


        LazyColumn {
            item {
                Text(
                    text = "Vill se",
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(viewModel.uiState.filmer.filter { !it.sedd }, key = { it.id }) { film ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        if (film.bildResId != null) {
                            Image(
                                painter = painterResource(id = film.bildResId),
                                contentDescription = film.titel,
                                modifier = Modifier.size(60.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Text(
                                text = film.titel.first().toString(),
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .padding(20.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            Text(text = film.titel)
                            Text(text = "År: ${film.ar}")
                            Text(text = "Genre: ${film.genre}")
                            Checkbox(
                                checked = film.sedd,
                                onCheckedChange = {
                                    viewModel.andraSedd(film.id)
                                }
                            )
                            Button(
                                onClick = { viewModel.taBortFilm(film.id) }
                            ) {
                                Text("Ta bort")
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Sett",
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(viewModel.uiState.filmer.filter { it.sedd }, key = { it.id }) { film ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        if (film.bildResId != null) {
                            Image(
                                painter = painterResource(id = film.bildResId),
                                contentDescription = film.titel,
                                modifier = Modifier.size(60.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Text(
                                text = film.titel.first().toString(),
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .padding(20.dp)
                            )
                        }

                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            Text(text = film.titel)
                            Text(text = "År: ${film.ar}")
                            Text(text = "Genre: ${film.genre}")
                            Checkbox(
                                checked = film.sedd,
                                onCheckedChange = {
                                    viewModel.andraSedd(film.id)
                                }
                            )
                            Button(
                                onClick = { viewModel.taBortFilm(film.id) }
                            ) {
                                Text("Ta bort")
                            }

                        }
                    }
                }
            }
        }

    }
}
}

