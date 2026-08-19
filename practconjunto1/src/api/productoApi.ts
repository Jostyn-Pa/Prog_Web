import type {Producto} from "../models/Producto.ts";

const API_URL = "http://localhost:9090/api/productos"

export const productoApi = {

    //findAll
    findAll: async ():Promise<Producto[]> => {
        try {
            const response = await fetch(API_URL)
            if(!response.ok) throw new Error("Error fetching producto api")
            return await response.json()
        } catch (error) {
            console.error(error)
            return []
        }
    },

    //findById
    findById: async(id:number): Promise<Producto|null> => {
        try {
            const response = await fetch(`${API_URL}/${id}`)
            if(response.status == 404) return null
            if(!response.ok) throw new Error("Error al buscar en el registro")
            return await response.json()
        } catch (error) {
            console.error(error)
            return null
        }
    },

    //CREATE
    create: async (nuevoProducto:Producto): Promise<boolean> => {
        try {
            const response = await fetch(API_URL, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(nuevoProducto)
            })
            return await response.status === 201
        } catch (error) {
            console.error(error)
            return false
        }
    },

    //UPDATE
    update: async(id: number, productoEditado: Producto): Promise<boolean> => {
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(productoEditado)
            })
            return await response.ok
        } catch (error) {
            console.error(error)
            return false
        }
    },

    //delete
    delete: async(id:number): Promise<boolean> => {
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: "DELETE"
            })
            return response.status === 204
        } catch (error) {
            console.error(error)
            return false
        }
    }

}