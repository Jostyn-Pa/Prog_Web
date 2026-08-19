import {useEffect, useState} from "react";
import type {Producto} from "../models/Producto.ts";
import {productoApi} from "../api/productoApi.ts";
import * as React from "react";
import {useNavigate} from "react-router-dom";

function GetProductos() {

    // 1. Pluralizado para mayor claridad
    const [productos, setProductos] = useState<Producto[]>([])

    // findById
    const [idBuscado, setIdBuscado] = useState<number>(0)
    const [productoBuscado, setProductoBuscado] = useState<Producto | null>(null)

    const navigate = useNavigate()

    const cargarTodosLosProductos = async () => {
        const datos = await productoApi.findAll()
        setProductos(datos)
    }

    useEffect(() => {
        cargarTodosLosProductos()
    }, []);

    const cargarFindById = async () => {
        const datos = await productoApi.findById(idBuscado)
        if(datos != null) {
            setProductoBuscado(datos)
        } else {
            alert("No se encontro el producto")
            setProductoBuscado(null)
        }
    }

    // 👇 1. CREAMOS LA FUNCIÓN DELETE
    const eliminarProducto = async (id: number) => {
        // Toque Pro: Evitar borrados por accidente
        const confirmar = window.confirm("¿Estás seguro de que deseas eliminar este producto?");
        if (!confirmar) return; // Si el usuario cancela, la función se detiene aquí

        // Llamamos al método delete de tu API
        const exito = await productoApi.delete(id);

        if (exito) {
            // Si Java dice que OK (204 No Content), recargamos la lista
            // Esto hará que el producto desaparezca mágicamente de la pantalla
            cargarTodosLosProductos();
        } else {
            alert("Hubo un error al eliminar el producto.");
        }
    }

    const cambiarId = (e: React.ChangeEvent<HTMLInputElement>) => {
        setIdBuscado(Number(e.target.value))
    }

    const siguiente = () => {
        navigate("/post")
    }

    return(
        <>
            <h1>PRODUCTOS</h1>
            <div>
                <h2>LISTA DE TODOS LOS PRODUCTOS</h2>
                {/* 2. Envolvemos los <li> en un <ul> */}
                <ul>
                    {
                        productos.map(pr => (
                            <li key={pr.id}>
                                {pr.nombre} - ${pr.precio}

                                {/* 👇 2. AGREGAMOS EL BOTÓN */}
                                {/* OJO: Siempre usar una función flecha () => si la función recibe parámetros */}
                                <button
                                    onClick={() => eliminarProducto(pr.id!)}
                                    style={{ marginLeft: '15px', color: 'red' }}
                                >
                                    Eliminar
                                </button>
                            </li>
                        ))
                    }
                </ul>
            </div>

            <div style={{padding: '10px', color: 'black'}}>
                <h2>BUSCAR POR ID</h2>
                <input
                    type='number'
                    value={idBuscado === 0 ? '' : idBuscado} // 3. Input 100% Controlado (truco para no mostrar el 0 inicial)
                    onChange={cambiarId}
                    placeholder="Ingrese ID"
                />
                <button onClick={cargarFindById}>Buscar</button>
            </div>

            {
                productoBuscado != null && (
                    <div style={{marginTop: '20px', padding: '10px', border: '1px solid gray'}}>
                        <h3>Resultado:</h3>
                        <p><strong>Nombre:</strong> {productoBuscado.nombre}</p>
                        <p><strong>Precio:</strong> ${productoBuscado.precio}</p>
                    </div>
                )
            }
            <hr/>
            <button onClick={siguiente}>Ir a POST</button>
        </>
    )
}

export default GetProductos;