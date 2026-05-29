
//import HolaMundo from "./HolaMundo.tsx";
//import HolaMundo2, {type Hello2Props} from "./HolaMundo2.tsx";
//import Contador from "./Contador.tsx";
//import * as React from "react";
import TarjetaPerfil, {type PerfilProps} from "./EjercicioPracticoAcumulado/TarjetaPerfil.tsx";
import GestorInteracciones from "./EjercicioPracticoAcumulado/GestorInteracciones.tsx";

function App() {

    //const [text, setText] = React.useState("mundo")

    const datosCanal: PerfilProps = {
        usuario: "Juan",
        membership: "Premium"
    }

    /*const handleClick = () => {
        if(text === "mundo") {
            setText("React")
        } else {
            setText("mundo")
        }
    }

    const props : Hello2Props = {
        name: "otro nombre",
        age: 50
    }*/

  return (
    <>
        {/*<HolaMundo name={"React"} />}
        {<PerfilUsuario name={"Juan"} profesion={"Developer"}/>}
        {<HolaMundo2 name={"ASD"} age={20}/>}
        {<TarjetaProducto nombre={"Granola"} precio={12.6} descuento={5.2}/>}
        {<hr/>}
        {<Contador/>}
        {<hr/>}
            {<SelectorCantidad/>*/}
        {/*Hola <span>{text}</span>}
        <br/>
        <button onClick={handleClick}>Click</button>
        <br/>
        <HolaMundo name="React" />
        <HolaMundo name= "Mundo"/>
        <HolaMundo name = "Jost"/>
        
        <hr/>
        <HolaMundo2 name={"Juan"} age = {20}/>
        <HolaMundo2 { ...props } />
        <HolaMundo2 name={props.name} age={props.age} />
        <br/>
    {<Contador />*/}

        <h1>Panel de Control</h1>
        <hr/>
        <TarjetaPerfil {...datosCanal}/>
        <GestorInteracciones creador={datosCanal.usuario}/>
    </>
  )
}

export default App
