
import './App.css'
import Contador from "./Contador.tsx";
import Sensor from "./EjerciciosConjuntos/Sensor.tsx";

function App() {

  return (
    <>
      <Contador/>
        <Sensor ubicacion={"Sala de estar"} tempMaxima={30}/>
    </>
  )
}

export default App
