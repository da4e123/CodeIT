import useEarthquakes from "../../hooks/useEarthquakes.js";

const EarthquakeTable = () => {
    const {
        earthquakes,
        loading,
        error,
        refreshEarthquakes,
        onDelete
    } = useEarthquakes();

    if (loading) return <p>Loading</p>;
    if (error) return <p>{error.message}</p>;

    return (
        <div>
            <div>
                <h2>Earthquakes</h2>
                <button onClick={refreshEarthquakes}>
                    Refresh
                </button>
            </div>

            <table border="1" cellPadding="8">
                <thead>
                <tr>
                    <th>Magnitude</th>
                    <th>Place</th>
                    <th>Time</th>
                    <th>Title</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                {earthquakes.map((eq) => (
                    <tr key={eq.id}>
                        <td>{eq.magnitude}</td>
                        <td>{eq.place}</td>
                        <td>{new Date(eq.time).toLocaleString()}</td>
                        <td>{eq.title}</td>
                        <td>
                            <button
                                onClick={() => onDelete(eq.id)}
                                className="btn btn-danger"
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default EarthquakeTable;