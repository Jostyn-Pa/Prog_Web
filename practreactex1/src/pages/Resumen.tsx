import {useLocation, useNavigate} from "react-router-dom";

function Resumen () {

    const navigate = useNavigate()

    const location = useLocation()

    const state = location.state as {name: string, age:string, carrera: string} | null

    const handleRegresar = () => {
        navigate("/")
    }

    return (
        <>
            <form>
                <h2>Bienvenido al Resumen</h2>
                {state && state.name && state.age && state.carrera
                ?
                    (
                        <div>
                            <p>Bienvenido {state.name}</p>
                            Edad:
                            {state.age}
                        </div>

                    )
                    :
                    (
                        <p>No se recibió ningun nombre</p>
                    )
                }
                <button onClick={handleRegresar}>Regresar</button>
            </form>
        </>
    )
}
export default Resumen