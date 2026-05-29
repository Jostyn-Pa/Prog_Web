
export interface PerfilProps {
    usuario: string
    membership?: string
}

function TarjetaPerfil(props: PerfilProps) {
    return (
        <>
            <h4>Canal de {props.usuario}</h4>
            {/* 3. Renderizado Condicional: Muestra la etiqueta <b> SOLO si tiene membresía */}
            {props.membership && <b style={{color:"red"}}>Membresía: {props.membership}</b>}
        </>
    )
}

export default TarjetaPerfil