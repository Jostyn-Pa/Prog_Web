import {type ChangeEvent, type FormEvent, useState} from "react";
import {productoApi} from "../api/productoApi.ts";
import {useNavigate} from "react-router-dom";

function PostProductos() {

    const navigate = useNavigate()
    const [nombre, setNombre] = useState('');
    const [precio, setPrecio] = useState(0);

    const crearProducto = async (e: FormEvent) => {
        e.preventDefault();
        const exito = await productoApi.create({
            nombre,
            precio
        })
        if(exito) {
            alert("Guardado")
            navigate("/")
        }
    }

    const handleSetNombre = (e: ChangeEvent<HTMLInputElement>) => {
        setNombre(e.target.value)
    }

    const handleSetPrecio = (e: ChangeEvent<HTMLInputElement>) => {
        setPrecio(Number(e.target.value))
    }

    const handleRegresar = () => {
        navigate("/")
    }

    const handleSiguiente = () => {
        navigate("/put")
    }

    return (
        <>
            <h2>CREAR PRODUCTOS</h2>

            <form onSubmit={crearProducto}>
                <input
                    type="text"
                    value={nombre} // 👈 1. Controlamos el input
                    onChange={handleSetNombre}
                    placeholder="NOMBRE DEL PRODUCTO"
                    required // Buena práctica: obligar a que lo llene
                />
                <br/>
                <br/>
                <input
                    type="number"
                    value={precio === 0 ? '' : precio} // 👈 1. Controlamos el input ocultando el 0 inicial
                    onChange={handleSetPrecio}
                    placeholder="Precio"
                    required
                />
                <br/>
                <br/>
                {/* 👇 2. Agregamos el botón para disparar el onSubmit del form */}
                <button type="submit">Guardar Producto</button>
            </form>
            <button onClick={handleRegresar}>Regresar</button>
            <button onClick={handleSiguiente}>PUT</button>
        </>
    )
}

export default PostProductos;