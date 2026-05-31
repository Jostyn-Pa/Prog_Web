import * as React from "react";
import {Link} from "react-router-dom";

function Login() {

    const [name, setName] = React.useState("")

    const handleChange = (event:React.ChangeEvent<HTMLInputElement>) => {
        setName(event.target.value)
    }

    return (
        <>
            <div style={{padding: "20px"}}>
                <h2>Inicia Sesión</h2>
                <input
                type="text"
                value={name}
                onChange={handleChange}
                placeholder={"Ingresa tu nombre"}
                />
                <br/>
                <Link to={`/panel/${name}`}>Entrar Al Panel</Link>
            </div>
        </>
    )
}

export default Login