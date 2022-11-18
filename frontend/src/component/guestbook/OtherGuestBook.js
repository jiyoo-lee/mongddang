import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import GetAxios from "../../utils/axios/GetAxios";
import PostAxios from "../../utils/axios/PostAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";
import GuestBookEditor from "./GuestBookEditor";

const OtherGuestBook = () => {
    const { userId } = useParams();
    const userIdOnToken = sessionStorage.getItem("userId");

    const [state, setState] = useState([]);
    const [guestBook, setGuestBook] = useState("");

    useEffect(()=>{
        GetAxios('/drawer/' + userId + '/guestbook', {param:{}}, (res) => {
            setState(res.data);
        });
    }, []);

    const handleRegistGuestBook = () => {
        PostAxios('/drawer/' + userId + '/guestbook', {contents: guestBook}, res=>window.location.reload());
    }

    const guestBookInput = 
    <div>
        <input type="text" value={guestBook} placeholder="새로운 방명록을 작성해주세요!" onChange={(e)=>(setGuestBook(e.target.value))}/>
        <button onClick={handleRegistGuestBook}>등록</button>
    </div>

    const edit = (guestBookId) => (
        <div> 
            <GuestBookEditor guestBookId={guestBookId} />

            <div onClick={()=>(DeleteAxios('/drawer/' + guestBookId, {}, (res)=>{
                window.location.reload();
            }))}>
                삭제
            </div>
        </div>
    )
    
    return (
        <>
            {userId}의 방명록
            <br/>
            <br/>
            {guestBookInput}
            <br/>
            {state.map(guestbook => (
                <div key={guestbook.guestBookId}>
                    ㅇ {guestbook.contents}
                    {guestbook.guestId == userIdOnToken && edit(guestbook.guestBookId)}
                    <br/>
                    {
                        guestbook.comment.commentId != null && 
                        <div key={guestbook.comment.commentId}>
                            {guestbook.comment.contents}
                            {guestbook.comment.commentCreateDatetime}
                        </div>
                    }
                    <br/>

                </div>
            ))}

        </>
    );
}

export default OtherGuestBook;