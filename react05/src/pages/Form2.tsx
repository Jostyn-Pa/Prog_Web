import {useLocation, useNavigate} from "react-router-dom";

function Form2 () {
    //Para navegar a otra ruta
    const navigate = useNavigate()
    //Para la llegada
    const location = useLocation()

    const state = location.state as {message : string } | null

    const handleRegresar = () => {
        navigate('/')
    }

    return (
        <>
            <form>
                <h2>Bienvenido al Formulario 2</h2>
                {state && state.message ?
                    (
                    <p>
                        Nombre recibido: <b>{state.message}</b>
                    </p>
                    ) :

                    (
                        <p>No se recibió ningún nombre</p>
                    )
                }
                <button type='button' onClick={handleRegresar}>Regresar</button>
            </form>
        </>
    )
}

export default Form2