import {Link, useParams} from "react-router-dom";
import {useState} from "react";

function DetallePelicula() {
    // 1. Lee el parámetro de la URL asegurando el contrato
    const {nombrePelicula} = useParams<{nombrePelicula:string}>()

    // 2. Crea un estado booleano para saber si es favorito (inicia en false)
    const[favorito, setFavorito] = useState(false)

    const alternarFavorito = () => {
        // 3. Cambia el estado al valor contrario (si es true, a false; si es false, a true)
        // Pista: setEsFavorito(!esFavorito)
        if(favorito == false){
            setFavorito(true)
        } else {
            setFavorito(false)
        }

        //Otra Manera
        //setFavorito(!favorito)
    };
    return (
        <>
            <div style={{padding: '20px', border:'2px solid purple'}}>
                {/* 4. Muestra el título. Si viene vacío, usa el paracaídas para mostrar "Película Desconocida" */}
                <h2>Viendo: {nombrePelicula || 'Película Desconocida'}</h2>
                {/* 5. Conecta el onClick y usa el ternario para cambiar el texto del botón */}
                <button onClick={alternarFavorito}>
                    {
                        favorito ? (
                            "Quitar de favoritos"
                        ) : (
                            "Añadir a favoritos"
                        )

                    }
                </button>
                <br/>
                <Link to={"/"}>Volver al catalogo</Link>
            </div>
        </>
    )
}

export default DetallePelicula