import type {Director} from "../models/Director.ts";

const URL_API = "http://localhost:8080/api/directores"

export const directorApi = {

    //findAll
    findAll: async(): Promise<Director[]> => {
        try {
            const response = await fetch(URL_API)
            return response.json()
        } catch (error) {
            console.error(error)
            return []
        }
    },

    //findById
    findById: async(id: number): Promise<Director|null> => {
        try {
            const response = await fetch(`${URL_API}/${id}`)
            if(response.status === 404) return null
            if(!response.ok) throw new Error("Could not find director")
            return response.json()
        } catch (error) {
            console.error(error)
            return null
        }
    },

    //Post
    create: async(nuevoDirector: Director): Promise<boolean> => {
        try {
            const response = await fetch(URL_API, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(nuevoDirector)
            })
            return await response.status === 201
        } catch (error) {
            console.error(error)
            return false
        }
    },

    //put
    actualizar: async(id:number, directorActualizado: Director): Promise<boolean> => {
        try {
            const response = await fetch(`${URL_API}/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(directorActualizado)
            })
            return response.ok
        } catch (error) {
            console.error(error)
            return false
        }
    },

    //delete
    delete: async(id:number): Promise<boolean> => {
        try {
            const response = await fetch(`${URL_API}/${id}`, {
                method: "DELETE"
            })
            return response.ok
        } catch (error) {
            console.error(error)
            return false
        }
    }
}