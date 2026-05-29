export interface Hello2Props {
    name: string
    age?: number
}

//props: nombre ------ Hello2Props: Tipo
function HolaMundo2(props: Hello2Props) {

    return(
        <>
            <h4>Hola Mundo {props.name}</h4>
            {/*SI le paso la edad, renderiza lo de la derecha*/}
            {props.age && <h4>Edad: {props.age}</h4>}
        </>
    )
}

export default HolaMundo2