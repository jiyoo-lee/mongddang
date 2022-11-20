import { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";


const AdminPaintings = () => {

    const navigate = useNavigate();
    const [ paintings, setPaintings ] = useState([]);
    const [ keyword, setKeyword ] = useState("");


    useEffect(()=>{
        GetAxios('/admin/paintings',{params:{}},(res)=>{
            setPaintings(res.data)
        })
    },[]) 
    
    const onChange = (e) =>{
        setKeyword(e.target.value)
    }

    const onSearch = () => {
        GetAxios('/admin/paintings/'+keyword,{params:{}},(res)=>{setPaintings(res.data)})
    }

    const onDelete = (paintingId) => {
        DeleteAxios('/admin/painting/'+paintingId,{params:{}},(res)=>{
            alert("삭제 처리 되었습니다.")
            window.location.reload()})
    }

    return (
    <div className="admin">
        <ul className="admin_wrapper">
        <div className="logo_box">
        <img className='logo' src={process.env.PUBLIC_URL + `.././img/present_logo.png`} 
                onClick={()=> {navigate('/admin')}} alt='logo'/>
                </div>
                <li className="deth_menu"> Member </li>
                <li className="admin_menu" onClick={()=>navigate('/admin')}> 회원 조회 </li>
                <hr/>
                <li className="deth_menu"> Painting </li>
                <li className="admin_menu" onClick={()=>window.location.reload()} > 그림 조회 </li>
                <hr/>
                <li className="deth_menu"> Contest </li>
                <li className="admin_menu" onClick={()=>navigate('/admin/arts-center')}> 공모전 관리 </li>
                <li className="admin_menu" onClick={()=>
                                                {sessionStorage.clear(); navigate('/'); }
                }> 로그아웃 </li>
        </ul>

    <section className="notice">
    <div className="page-title">
        <div className="container">
            <h3>그림 조회</h3>
        </div>
    </div>
    <div id="board-search">
        <div className="container">
            <div className="search-window">
                    <div className="search-wrap">
                        <input type="text"  placeholder="그림제목을 입력해주세요." value={keyword} onChange={(e)=>onChange(e)}/>
                        <button type="submit" className="btn btn-dark" onClick={onSearch}>검색</button>
                    </div>
            </div>
        </div>
    </div>

    <div id="board-list">
        <div className="container">
            <table className="board-table">
                <thead>
                <tr>
                    <th scope="col" className="th-num">아이디</th>
                    <th scope="col" className="th-num">작성자</th>
                    <th scope="col" className="th-title">제목</th>
                    <th scope="col" className="th-date">그림</th>
                    <th scope="col" className="th-date">내용</th>
                    <th scope="col" className="th-date">작성일자</th>
                    <th scope="col" className="th-date">삭제</th>
                </tr>
                </thead>
                <tbody>
                {paintings.length > 0 ? paintings.map(painting => (
                    
                    <tr key={painting.paintingId}>
                        <td>{painting.memberId}</td>
                        <td>
                        {painting.name}
                        </td>
                        <td>{painting.paintingName}</td>
                        <td><img className="items_img" src={painting.paintingUrl}/></td>
                        <td>{painting.description}</td>
                        <td>{painting.createDatetime}</td>
                        <td onClick={()=>onDelete(painting.paintingId)}> 삭제 </td>
                </tr>
                
                )): <></>}
        
                </tbody>
            </table>
        </div>
    </div>

    </section>
    </div>
);
}

export default AdminPaintings;