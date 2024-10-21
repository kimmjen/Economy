import YoutubeStreamArea from "@/widgets/youtube/YoutubeStreamArea.jsx";

export const YoutubeStream = () => {
    return (
        <div className="mb-12 grid gap-y-10 gap-x-6 md:grid-cols-2 xl:grid-cols-1">
            <YoutubeStreamArea/>
            {/*<YoutubeStreamArea/>*/}
        </div>
    )
}