import { useCallback, useEffect, useState } from "react";
import earthquakeRepository from "../repository/earthquakeRepository.js";

const useEarthquakes = () => {
    const [earthquakes, setEarthquakes] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [mapData, setMapData] = useState([]);

    const findAll = useCallback(() => {
        setLoading(true);

        earthquakeRepository.findAll()
            .then((response) => {
                setEarthquakes(response.data);
            })
            .catch((error) => {
                setError(error);
            })
            .finally(() => {
                setLoading(false);
            });
    }, []);

    const fetchEarthquakes = useCallback(() => {
        setLoading(true);
        setError(null);

        earthquakeRepository.filtered()
            .then((response) => {
                setEarthquakes(response.data);
            })
            .catch((error) => {
                setError(error);
            })
            .finally(() => {
                setLoading(false);
            });
    }, []);

    const refreshEarthquakes = useCallback(() => {
        setLoading(true);

        earthquakeRepository.refreshed()
            .then(() => earthquakeRepository.findAll())
            .then((response) => {
                setEarthquakes(response.data);
            })
            .catch((error) => {
                setError(error);
            })
            .finally(() => {
                setLoading(false);
            });
    }, []);

    const onDelete = useCallback((id) => {
        console.log("Deleting ID:", id);
        earthquakeRepository.delete(id)
            .then(() => {
                setEarthquakes((prev) =>
                    prev.filter((eq) => eq.id !== id)
                );
            })
            .catch((error) => {
                setError(error);
            });
    }, []);

    const fetchMapData = useCallback(() => {
        earthquakeRepository
            .mapData()
            .then((response) => {
                console.log(response.data);
                setMapData(response.data);
            })
            .catch((error) => setError(error));
    }, []);

    useEffect(() => {
        findAll()
        fetchMapData()
    }, [findAll, fetchMapData]);

    return {
        earthquakes,
        loading,
        error,
        refreshEarthquakes,
        findAll,
        onDelete,
        fetchMapData
    };
};

export default useEarthquakes;