import { type ChangeEvent, type FormEvent, useEffect, useState } from "react";
import { productoApi } from "../api/productoApi.ts";
import { useNavigate, useParams } from "react-router-dom";

function PutProductos() {
    const navigate = useNavigate();

    // 1. Capturamos el ID que viene en la URL (ej: /editar/5)
    // El nombre "id" debe coincidir exactamente con el que pongas en App.tsx
    const { id } = useParams<{ id: string }>();
    const idNumero = Number(id);

    // Estados para los inputs del formulario
    const [nombre, setNombre] = useState('');
    const [precio, setPrecio] = useState(0);

    // 2. PASO 1: Cargar los datos actuales apenas se abra la página
    const cargarDatosProducto = async () => {
        if (!idNumero) return;

        const productoActual = await productoApi.findById(idNumero);
        if (productoActual) {
            // Pre-llenamos los estados con lo que ya está guardado en PostgreSQL
            setNombre(productoActual.nombre);
            setPrecio(productoActual.precio);
        } else {
            alert("El producto no existe");
            navigate("/"); // Si no existe, lo botamos a la lista
        }
    };

    useEffect(() => {
        cargarDatosProducto();
    }, [idNumero]); // Se ejecuta cuando el ID esté listo

    // 3. PASO 2: Enviar los cambios modificados (El PUT real)
    const editarProducto = async (e: FormEvent) => {
        e.preventDefault();

        // Llamamos al método update de tu API central pasándole el ID y los nuevos datos
        const exito = await productoApi.update(idNumero, {
            nombre,
            precio
        });

        if (exito) {
            alert("¡Producto actualizado con éxito!");
            navigate("/"); // Redireccionamos a la lista de productos
        } else {
            alert("No se pudo actualizar el producto");
        }
    };

    const handleSetNombre = (e: ChangeEvent<HTMLInputElement>) => {
        setNombre(e.target.value);
    };

    const handleSetPrecio = (e: ChangeEvent<HTMLInputElement>) => {
        setPrecio(Number(e.target.value));
    };

    const handleRegresar = () => {
        navigate("/post")
    }

    return (
        <>
            <h2>EDITAR PRODUCTO (ID: {id})</h2>

            <form onSubmit={editarProducto}>
                <label>Nombre:</label>
                <br />
                <input
                    type="text"
                    value={nombre} // Input controlado con el valor cargado de la BD
                    onChange={handleSetNombre}
                    placeholder="Nombre del producto"
                    required
                />
                <br /><br />

                <label>Precio:</label>
                <br />
                <input
                    type="number"
                    value={precio === 0 ? '' : precio} // Input controlado
                    onChange={handleSetPrecio}
                    placeholder="Precio"
                    required
                />
                <br /><br />

                <button type="submit">Actualizar PUT</button>
                <button type="button" onClick={() => navigate("/")} style={{ marginLeft: '10px' }}>
                    Cancelar
                </button>
                <br/>
                <button onClick={handleRegresar}>REGRESAR A POST</button>
            </form>
        </>
    );
}

export default PutProductos;