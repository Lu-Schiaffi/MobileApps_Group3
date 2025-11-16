package com.appdelclima.presentation.ciudades

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appdelclima.repository.modelos.GeoCityDto

@Composable
fun CiudadesView(
    estado: CiudadesEstado,
    onIntent: (CiudadesIntencion) -> Unit,
    onElegirCiudad: (GeoCityDto) -> Unit,
    onUsarGeo: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() })
    { innerPadding ->
        Column(Modifier
            .fillMaxSize()
            //.padding(16.dp)
            .padding(innerPadding)
        ) {
            Text("Ciudades", style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = estado.query,
                onValueChange = { onIntent(CiudadesIntencion.QueryCambio(it)) },
                label = { Text("Buscar ciudad") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Row { Button(onClick = onUsarGeo) { Text("Usar mi ubicación") } }

            if (estado.cargando) {
                Spacer(Modifier.height(12.dp)); Text("Cargando…")
            }
            if (estado.error != null) {
                Spacer(Modifier.height(12.dp)); Text(
                    estado.error!!,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(Modifier.height(12.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(estado.resultados) { city ->
                    Surface(
                        tonalElevation = 2.dp, modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onElegirCiudad(city) }
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${city.name} (${city.country})")
                            Text("Elegir")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text("Aplicacion del clima") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}
