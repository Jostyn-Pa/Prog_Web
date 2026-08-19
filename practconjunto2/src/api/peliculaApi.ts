import type {Pelicula} from "../models/Pelicula.ts";

const URL_API = "http://localhost:8080/api/peliculas"

export const peliculaApi = {

    //findAll
    findAll: async (): Promise<Pelicula[]> => {
        try {
            const response = await fetch(URL_API)
            return await response.json()
        } catch (error) {
            console.error(error)
            return []
        }
    },

    //findById
    findById: async (id: number): Promise<Pelicula | null> => {
        try {
            const response = await fetch(`${URL_API}/${id}`)
            if (response.status === 404) return null
            if (!response.ok) throw new Error("Producto not found")
            return await response.json()
        } catch (error) {
            console.error(error)
            return null
        }
    },

    //POST
    create: async (nuevaPelicula: Pelicula): Promise<boolean> => {
        try {
            const response = await fetch(URL_API, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(nuevaPelicula)
            })
            return await response.status === 201
        } catch (error) {
            console.error(error)
            return false
        }
    },

    //UPDATE
    update: async (id: number, peliculaActualizada: Pelicula): Promise<boolean> => {
        try {
            const response = await fetch(`${URL_API}/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(peliculaActualizada)
            })
            return await response.ok
        } catch (error) {
            console.error(error)
            return false
        }
    },

    //delete
    delete: async (id: number): Promise<boolean> => {
        try {
            const response = await fetch(`${URL_API}/${id}`, {
                method: "DELETE"
            })
            return await response.status === 204
        } catch (error) {
            console.error(error)
            return false
        }
    }
}