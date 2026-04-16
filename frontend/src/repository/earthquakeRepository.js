import axiosInstance from "../axios/axios.js";

const earthquakeRepository = {

    findAll : async () => {
        return await axiosInstance.get(`/earthquakes`);
    },
    filtered: async () => {
        return await axiosInstance.get('/earthquakes/filtered');
    },

    refreshed: async () => {
        return await axiosInstance.post('/earthquakes/refresh?afterTime=2');
    },

    delete: async (id) => {
        return await axiosInstance.delete(`/earthquakes/${id}`);
    },

    mapData: async () => {
        return await axiosInstance.get(`/earthquakes/map`);
    }
}

export default earthquakeRepository;