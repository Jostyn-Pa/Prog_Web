function FormularioProducto() {

    function buscarProducto(formData: FormData) {
        const producto = formData.get('producto')
        const precio = formData.get('precio')

        alert(`${producto} , ${precio}`);
    }

    return (
        <>
            <form action={buscarProducto}>
                <div>
                    Producto: <input type='text' name='producto' />
                    <br/>
                    Precio: <input type='text' name='precio'/>
                    <br/>
                    <input type='submit' value='Buscar'/>
                </div>
            </form>
        </>
    )
}

export default FormularioProducto